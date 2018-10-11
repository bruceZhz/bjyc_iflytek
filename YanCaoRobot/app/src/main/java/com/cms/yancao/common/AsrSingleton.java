package com.cms.yancao.common;

import android.text.TextUtils;
import android.util.Log;

import com.cms.yancao.RobotApplication;
import com.cms.yancao.app.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class AsrSingleton {
    private static final String TAG = "AsrSingleton";
    private static final int sSleepTime = Config.getSleepTime();

    private MainActivity mActivity;
    private Callback mCallback;

    private WakeUpHelper mWakeUp;
    private IatHelper mIatHelper;
    private TtsHelper mTtsHelper;
    private WeatherHelper mWeatherHelper;

    private int ltNumber;  //短句子连续没有语义的次数
    private int gtNumber;  //长句子连续没有语义的次数

    private boolean isWakeup = false; //是否唤醒

    private boolean isSpeaking = false; //是否正在进行不可打断的语音播报
    private boolean isVideoPlaying = false; //是否正在播放视频
    private boolean isActionExecuting = false; //是否正在进行不可打断的动作

    private AsrSingleton() {
        mWeatherHelper = new WeatherHelper();
    }

    public void init(MainActivity activity, Callback callback) {
        mCallback = callback;
        mActivity = activity;

        isWakeup = false;
        isSpeaking = false;
        isVideoPlaying = false;
        isActionExecuting = false;

        mIatHelper = new IatHelper(activity);
        mTtsHelper = new TtsHelper(activity);

        mWakeUp = new WakeUpHelper(activity);
        mWakeUp.setWakeUpListener(new WakeUpHelper.WakeUpListener() {
            @Override
            public void onWakeUp(int angle) {
                if (!isWakeup) {
                    startInteraction();
                }
            }
        });

        mIatHelper.setCallback(new IatHelper.Callback() {
            @Override
            public void onResult(String msg, boolean isLast) {
                msg = msg.replaceAll("^[，|。]", "");
                if (!TextUtils.isEmpty(msg)) {
                    requestAnswer(msg);
                }
                //mock(msg);
            }
        });
    }

    /**
     * 开始交互
     */
    private void startInteraction() {
        isWakeup = true;
        mActivity.showHomeFragment();
        startRecognizer();
        mTtsHelper.speak(Config.getWelcome());
        startTimerForSleep();
    }

    /**
     * 停止交互
     */
    private void stopInteraction() {
        isWakeup = false;
        isSpeaking = false;
        isVideoPlaying = false;
        isActionExecuting = false;

        mActivity.showWakeFragment();
        stopRecognizer();
        if (mTimerDisposable != null) {
            mTimerDisposable.dispose();
        }
    }

    private void mock(String msg) {
        try {
            if (msg.contains("视频")) {
                JSONObject obj = new JSONObject();
                obj.put("type", 2);
                obj.put("vidoURL", "https://media.w3.org/2010/05/sintel/trailer.mp4");
                dealWithAnswer(obj);
            }
            if (msg.contains("中华烟")) {
                JSONObject obj = new JSONObject();
                obj.put("type", 1);
                obj.put("answer", "\u3000\u3000中华香烟简称“中华”，是1951年创立的香烟品牌，1952年中华烟草公司并入上海烟草公司，“中华”烟以其独特的品味和魅力征服了消费者，赢得了中国第一品牌——国烟的美誉。\n" +
                        "\u3000\u3000“中华”牌一直是中国高档烟消费市场的第一品牌，成为了社会精英展示身份、体现价值的重要附属品。在过去的五十多年中，中华不断树立着谦逊但不失尊贵的风范，赢得了高端消费人群的普遍推崇，成为了中国高档烟市场的绝对领导者。");
                dealWithAnswer(obj);
            }

            if (msg.contains("天气")) {
                JSONObject obj = new JSONObject();
                obj.put("type", 1);
                obj.put("answer", "今天天气多云");
                dealWithWeather(1);
            }

            if (msg.contains("列表")) {
                JSONObject obj = new JSONObject();
                obj.put("type", 4);
                obj.put("answer", "下面是所有商品列表：");
                JSONArray array = new JSONArray();
                for (int i = 0; i < 10; i++) {
                    JSONObject item = new JSONObject();
                    item.put("title", "中华烟" + i);
                    item.put("id", i);
                    array.put(i, item);
                }
                obj.put("list", array);
                dealWithAnswer(obj);
            }

            if (msg.contains("商品")) {
                JSONObject obj = new JSONObject();
                obj.put("type", 3);
                obj.put("answer", "中华烟价格500元");
                JSONObject goods = new JSONObject();
                goods.put("name", "中华烟");
                goods.put("price", "380元");
                goods.put("address", "北京烟草公司");
                goods.put("detail", "\u3000\u3000中华香烟简称“中华”，是1951年创立的香烟品牌，1952年中华烟草公司并入上海烟草公司，“中华”烟以其独特的品味和魅力征服了消费者，赢得了中国第一品牌——国烟的美誉。");
                JSONObject pro = new JSONObject();
                pro.put("price", "320元");
                pro.put("content", "中华烟正在打折");
                goods.put("promotions", pro);
                obj.put("goods", goods);
                dealWithAnswer(obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求语义答案
     *
     * @param msg
     */
    public void requestAnswer(final String msg) {
        mCountDown = 2 * sSleepTime; //重置休眠的倒计时
        Disposable d = HttpModel.loadAnswer("", msg)
                .subscribe(new Consumer<JSONObject>() {
                    @Override
                    public void accept(JSONObject obj) throws Exception {
                        Log.e(TAG, "answer:" + obj.toString());
                        dealWithRejection(msg, obj);
                        if (obj.optInt("rc") != -1) { //有语义
                            int index = mWeatherHelper.isWeatherAnswer(obj.optString("answer"));
                            if (index != -1) {
                                dealWithWeather(index);
                            } else {
                                dealWithAnswer(obj);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 处理语义答案
     *
     * @param obj
     */
    private void dealWithAnswer(JSONObject obj) {
        int type = obj.optInt("type"); //1.纯文本,2.播放视频,3.商品信息类,4.列表
        if (mCallback != null) {
            mCallback.onResult(type, obj);
        }

        executeSpeak(type, obj);

        executeAction(type, obj.optJSONObject("action"));

    }

    /**
     * 播放语音答案
     *
     * @param type
     * @param obj
     */
    private void executeSpeak(int type, JSONObject obj) {
        String speak = null;
        switch (type) {
            case 1:
            case 3:
            case 4:
                speak = obj.optString("answer");
                break;
        }

        if (obj.optBoolean("isSleep")) { //进入休眠
            stopRecognizer();
            if (Utils.isEmpty(speak)) {
                stopInteraction();
            } else {
                mTtsHelper.speak(speak, new TtsHelper.Callback() {
                    @Override
                    public void onCompleted() {
                        stopInteraction();
                    }
                });
            }
            return;
        }

        if (!Utils.isEmpty(speak)) {
            boolean canListen = obj.optBoolean("canListen");
            if (canListen) {
                mTtsHelper.speak(speak);
            } else {
                setSpeaking(true);
                stopRecognizer();
                mTtsHelper.speak(speak, new TtsHelper.Callback() {
                    @Override
                    public void onCompleted() {
                        setSpeaking(false);
                        startRecognizer();
                    }
                });
            }
        }
    }

    /**
     * 执行机器人动作
     *
     * @param type
     * @param action
     */
    private void executeAction(int type, JSONObject action) {
        if (action.isNull("type")){
            return;
        }
        int actionType = action.optInt("type"); //1.普通动作，2.路径规划
        if (actionType == 1) {
            RobotControl.executeAction(action.optInt("action"));
        }else if(actionType == 2){
            try {
                JSONObject address = action.getJSONObject("address");
                float x = (float) address.getDouble("coordinateX");
                float y = (float) address.getDouble("coordinateY");
                float angle = (float) address.getDouble("angle");
                WalkController.walk(x,y,angle,mTtsHelper);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理拒识
     *
     * @param msg
     * @param obj
     */
    private void dealWithRejection(String msg, JSONObject obj) {
        if (obj.optInt("rc") != -1) {
            ltNumber = 0;
            gtNumber = 0;
        } else {
            if (msg.length() <= Config.getLtNumber()) {
                ltNumber++;
                gtNumber = 0;
            }
            if (ltNumber >= 3) {
                ltNumber = 0;
                mTtsHelper.speak(Config.getLtRejection());
            }

            if (msg.length() >= Config.getGtNumber()) {
                gtNumber++;
                ltNumber = 0;
            }
            if (gtNumber >= 3) {
                gtNumber = 0;
                mTtsHelper.speak(Config.getGtRejection());
            }
        }
    }

    /**
     * 处理天气答案
     *
     * @param index
     */
    private void dealWithWeather(int index) {
        Disposable d = mWeatherHelper.loadWeather(index).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (!TextUtils.isEmpty(s)) {
                    JSONObject obj = new JSONObject();
                    obj.put("type", 1);
                    obj.put("answer", s);
                    dealWithAnswer(obj);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }


    private int mCountDown;
    private Disposable mTimerDisposable;

    /**
     * 自动休眠定时器
     */
    private void startTimerForSleep() {
        mCountDown = 2 * sSleepTime;
        mTimerDisposable = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (mTtsHelper.isSpeaking() || isSpeaking || isVideoPlaying || isActionExecuting) {
                            mCountDown = 2 * sSleepTime;
                        } else {
                            mCountDown--;
                            if (mCountDown == sSleepTime) {
                                new TtsHelper(RobotApplication.sInstance).speak(Config.getSleepTip());
                            }
                            if (mCountDown == 0) {
                                stopInteraction();
                            }
                        }
                    }
                });
    }

    /**
     * 开始自动语音识别
     */
    public void startRecognizer() {
        if (!isWakeup) {
            return;
        }
        if (isSpeaking || isVideoPlaying || isActionExecuting) {
            return;
        }
        mIatHelper.setOpenAsr(true);
        mIatHelper.startListening();
    }

    /**
     * 关闭自动语音识别
     */
    public void stopRecognizer() {
        mIatHelper.stopListening();
        mIatHelper.setOpenAsr(false);
    }

    public void setVideoPlaying(boolean videoPlaying) {
        isVideoPlaying = videoPlaying;
    }

    public void setSpeaking(boolean speaking) {
        isSpeaking = speaking;
    }

    public void setActionExecuting(boolean actionExecuting) {
        isActionExecuting = actionExecuting;
    }

    public void unInit() {
        mWakeUp.unregister();
        mActivity = null;
        if (mTimerDisposable != null) {
            mTimerDisposable.dispose();
        }
        stopRecognizer();
        mTtsHelper.destroy();
        mIatHelper.destroy();
    }

    public static AsrSingleton getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static AsrSingleton instance = new AsrSingleton();
    }

    public interface Callback {
        void onResult(int type, JSONObject obj);
    }

}
