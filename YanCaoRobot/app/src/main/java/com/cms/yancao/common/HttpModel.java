package com.cms.yancao.common;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpModel {
    public static  final String prefix = "http://10.5.223.538080/showimage.do?name=";

    private static OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(15 * 1000, TimeUnit.MILLISECONDS)
            .writeTimeout(15 * 1000, TimeUnit.MILLISECONDS)
            .readTimeout(15 * 1000, TimeUnit.MILLISECONDS)
            .build();


    public static Observable<JSONObject> loadAnswer(String robot, String content) {
        String url = "http://10.5.223.53:8080/robot/getAnswer";
        RequestBody body = new FormBody.Builder()
                .add("sourceTerminal", "5")
                .add("terminalId", "")
                .add("iss_uid", "")
                .add("itemId", robot)
                .add("type", "0")
                .add("content", content)
                .build();
        final Request request = new Request.Builder().url(url).post(body).build();
        Observable<JSONObject> observable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        Response response = mOkHttpClient.newCall(request).execute();
                        e.onNext(response.body().string());
                        e.onComplete();
                    }
                })
                .map(new Function<String, JSONObject>() {
                    @Override
                    public JSONObject apply(String s) throws Exception {
                        return new JSONObject(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable<JSONObject> loadConfig(String robotId){
        String url = "http://10.5.223.53:8080/robot/getRobotInfoByRobotID";
        RequestBody body = new FormBody.Builder()
                .add("robotID", robotId)
                .build();
        final Request request = new Request.Builder().url(url).post(body).build();
        Observable<JSONObject> observable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        Response response = mOkHttpClient.newCall(request).execute();
                        e.onNext(response.body().string());
                        e.onComplete();
                    }
                })
                .map(new Function<String, JSONObject>() {
                    @Override
                    public JSONObject apply(String s) throws Exception {
                        return new JSONObject(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }


    public static Observable<JSONObject> loadWeather() {
        String url = "http://t.weather.sojson.com/api/weather/city/101010100";
        final Request request = new Request.Builder().url(url).build();
        Observable<JSONObject> observable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        Response response = mOkHttpClient.newCall(request).execute();
                        e.onNext(response.body().string());
                        e.onComplete();
                    }
                })
                .map(new Function<String, JSONObject>() {
                    @Override
                    public JSONObject apply(String s) throws Exception {
                        return new JSONObject(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}
