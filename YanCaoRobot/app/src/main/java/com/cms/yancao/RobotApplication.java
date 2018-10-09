package com.cms.yancao;

import android.app.Application;

import com.cms.yancao.common.RobotControl;
import com.cms.yancao.common.WalkController;
import com.iflytek.aipsdk.common.SpeechUtility;
import com.iflytek.util.Logs;

public class RobotApplication extends Application {

    public static RobotApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        SpeechUtility.createUtility(this, null);
        RobotControl.init(this);
        WalkController.init(this);
        Logs.setPrintFlag(false);
    }
}
