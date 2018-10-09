package com.cms.yancao.common;

import android.content.Context;

import com.android.iflytek.IflytekManager;
import com.robot.adapter.bean.RobotPose;
import com.robot.ros.IRosListener;

public class WalkController {

    private final String TAG = RobotControl.class.getSimpleName();
    private static IflytekManager sIflytekManager;


    public static void init(Context context) {
        sIflytekManager = (IflytekManager) context.getSystemService("iflytek");

        sIflytekManager.registerRosListener(new IRosListener() {
            @Override
            public void onPose(RobotPose robotPose) {

            }

            @Override
            public void onBattery(int i) {

            }
        });
    }


    public static void walk(final float x,final float y,final float angle){
        sIflytekManager.rosConnect("192.168.11.1",1445);
        RobotPose robotPose = new RobotPose(x,y,0,angle,0,0);
        int i = sIflytekManager.rosSetDestPose(robotPose);
        sIflytekManager.rosLoadMap("/sdcard/19楼地图.stcm");
//        AsrSingleton.getInstance().stopRecognizer();
    }

}
