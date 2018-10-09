package com.cms.yancao.common;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.android.iflytek.IflytekManager;
import com.cms.yancao.RobotApplication;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RobotControl {
    private final String TAG = RobotControl.class.getSimpleName();
    private static IflytekManager sIflytekManager;

    public static void init(Context context) {
        sIflytekManager = (IflytekManager) context.getSystemService("iflytek");
    }

    private static void executeAction(int action, int delay) {
        sIflytekManager.racAction(action);
        if (delay > 0) {
            Disposable d = Observable.timer(delay, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            sIflytekManager.racAction(0);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable e) throws Exception {
                            sIflytekManager.racAction(0);
                        }
                    });
        }
    }


    public static void executeAction(int action) {
        switch (action) {
            case 101: //欢迎动作
                executeAction(2, 4);
                break;
            case 102: //摇头
                executeAction(5, 4);
                break;
            case 103: //点头
                executeAction(8, 0);
                break;
        }
    }

    public static String getRobotGuid() {
        String guid = null;
        WifiManager wifi = (WifiManager) RobotApplication.sInstance.getSystemService(Context.WIFI_SERVICE);
        if (null != wifi) {
            String macAddress = wifi.getConnectionInfo().getMacAddress();
            guid = TextUtils.isEmpty(macAddress) ? null : macAddress.replace(":", "");
        }
        if (TextUtils.isEmpty(guid)) {
            guid = "1234567890aaabbb";
        }
        return guid;
    }

}
