package com.cms.yancao.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.android.iflytek.IflytekManager;
import com.robot.adapter.bean.RobotPose;
import com.robot.ros.IRosListener;

public class WalkController {

    private final String TAG = RobotControl.class.getSimpleName();
    private static IflytekManager sIflytekManager;
    private static RobotPose robotPose;
    private static TtsHelper ttsHelper;
    private static RobotPose homePose  = Config.getHome();


    public static void init(Context context) {
        sIflytekManager = (IflytekManager) context.getSystemService("iflytek");
        sIflytekManager.rosConnect("192.168.11.1",1445);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sIflytekManager.rosLoadMap("/data/19楼地图.stcm");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IflytekManager.ACTION_ROS_GOAL_STATUS);
        context.registerReceiver(wlakReceiver, intentFilter);
    }


    public static void walk(final float x,final float y,final float angle,TtsHelper tts){
        sIflytekManager.rosConnect("192.168.11.1",1445);
        robotPose = new RobotPose(x,y,0,angle,0,0);
        int i = sIflytekManager.rosSetDestPose(robotPose);
        ttsHelper = tts;
        AsrSingleton.getInstance().setActionExecuting(true);
        AsrSingleton.getInstance().stopRecognizer();

    }


    private static BroadcastReceiver wlakReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(IflytekManager.ACTION_ROS_GOAL_STATUS)){
                int status = intent.getIntExtra(IflytekManager. EXTRA_ROS_GOAL_STATUS, 0);
                if(2==status) {
                    if (eqHome()) {
                        ttsHelper.speak("您可以问我问题，我可以回答您哦");
                        AsrSingleton.getInstance().setActionExecuting(false);
                        AsrSingleton.getInstance().startRecognizer();
                    } else {
                        ttsHelper.speak("我已经到达目的地");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        robotPose = homePose;
                        int i = sIflytekManager.rosSetDestPose(homePose);


                    }
                }

                }
            }
    };


    private static boolean eqHome(){
        if(robotPose.getX() == homePose.getX()&&robotPose.getY() == homePose.getY() && robotPose.getYaw() == homePose.getYaw()){
            return  true;
        }else{
            return false;
        }
    }
}
