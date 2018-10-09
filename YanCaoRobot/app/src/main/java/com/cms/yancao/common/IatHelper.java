package com.cms.yancao.common;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.cms.yancao.R;
import com.iflytek.aipsdk.asr.RecognizerListener;
import com.iflytek.aipsdk.asr.RecognizerResult;
import com.iflytek.aipsdk.asr.SpeechRecognizer;
import com.iflytek.aipsdk.common.InitListener;
import com.iflytek.aipsdk.util.ErrorCode;
import com.iflytek.aipsdk.util.SpeechConstant;
import com.iflytek.aipsdk.util.SpeechError;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class IatHelper {
    private static final String TAG = "IatHelper";

    private Context mContext;
    private SpeechRecognizer mIat;
    private boolean isOpenAsr = true; //是否持续监听

    private Callback mCallback;

    public IatHelper(Context context) {
        mContext = context.getApplicationContext();
        mIat = SpeechRecognizer.createRecognizer(mContext, mInitListener);
        setParams();
    }

    public void startListening() {
        if (mIat.isListening()) {
            mIat.stopListening();
        }
        mIat.startListening(mRecognizerListener);
    }

    public void stopListening() {
        if (mIat.isListening()) {
            mIat.stopListening();
        }
    }

    /**
     * 设置是否持续监听
     *
     * @param asr
     */
    public void setOpenAsr(boolean asr) {
        isOpenAsr = asr;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public void destroy() {
        if (mIat != null) {
            mIat.cancel();
            mIat.destroy();
            mIat = null;
        }
    }

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.e(TAG, "SpeechRecognizer init() code = " + code);
            if (code == ErrorCode.SUCCESS) {

            } else {
                Utils.showTip(mContext, "初始化失败,错误码：" + code);
            }
        }
    };


    private boolean setParams() {
        mIat.setParameter(SpeechConstant.PARAMS, null);
        String params = mContext.getResources().getString(R.string.rcg_params).trim();
        mIat.setParameter(SpeechConstant.PARAM, params);
        return true;
    }


    private RecognizerListener mRecognizerListener = new RecognizerListener() {
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        public void onBeginOfSpeech() {

        }

        @SuppressLint("CheckResult")
        @Override
        public void onEndOfSpeech() {
            Log.e(TAG, "onEndOfSpeech");
            if (isOpenAsr) {
                Observable.timer(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startListening();
                    }
                });
            }
        }

        @Override
        public void onResult(RecognizerResult resultObj, boolean isLast) {
            Log.e(TAG, "onResult:" + resultObj.getResultString());
            if (mCallback != null) {
                try {
                    JSONObject obj = new JSONObject(resultObj.getResultString());
                    String result = obj.optString("result");
                    if (!TextUtils.isEmpty(result)) {
                        mCallback.onResult(result, isLast);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onError(SpeechError speechError) {
            Log.e(TAG, "onError:" + speechError);
            if (isOpenAsr) {
                startListening();
            }
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }

        @Override
        public void onWakeUp(String s, int i) {
            Log.e(TAG, "onWakeUp:" + s);
        }
    };

    public interface Callback {

        void onResult(String msg, boolean isLast);
    }

}
