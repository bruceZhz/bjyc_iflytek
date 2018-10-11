package com.cms.yancao.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.cms.yancao.RobotApplication;
import com.robot.adapter.bean.RobotPose;

import org.json.JSONObject;

public class Config {
    private static SharedPreferences sp = RobotApplication.sInstance.getSharedPreferences("config", Context.MODE_PRIVATE);

    public static String getWelcome() {
        return sp.getString("welcome", "欢迎使用烟草机器人。");
    }

    public static int getLtNumber() {
        return sp.getInt("ltNumber", 2);
    }

    public static String getLtRejection() {
        return sp.getString("deniedWordOne", "环境嘈杂，请靠近我的正前端说话。");
    }

    public static int getGtNumber() {
        return sp.getInt("gtNumber", 10);
    }

    public static String getGtRejection() {
        return sp.getString("deniedWordTwo", "不好意思，我没听懂你的意思。");
    }

    public static int getSleepTime() {
        return sp.getInt("sleepTime", 30);
    }

    public static String getSleepTip() {
        return sp.getString("overtimeWord", "您好，我没有听到你说的话，我将在十秒后进入待机页面。");
    }

    public static String getAddress() {
        return sp.getString("address", "");
    }

    public static String getLocation() { //机器人所在营业厅
        return sp.getString("location", "");
    }

    public static RobotPose getHome() {
        return new RobotPose(sp.getFloat("x",0),sp.getFloat("y",0),0,sp.getFloat("angle",0),0,0);
    }

    public static void putConfig(JSONObject obj) {
        try {
            SharedPreferences.Editor editor = sp.edit();

            editor.putString("welcome", obj.optString("welcome"));

            editor.putInt("ltNumber", obj.optInt("ltNumber"));
            editor.putString("deniedWordOne", obj.optString("deniedWordOne"));

            editor.putInt("gtNumber", obj.optInt("gtNumber"));
            editor.putString("deniedWordTwo", obj.optString("deniedWordTwo"));

            editor.putInt("sleepTime", obj.optInt("sleepTime"));
            editor.putString("overtimeWord", obj.optString("overtimeWord"));

            editor.putString("address", obj.optString("address"));
            editor.putString("location", obj.optString("location"));
            JSONObject map = obj.getJSONObject("map");
            editor.putFloat("x",(float) map.getDouble("coordinateX"));
            editor.putFloat("y",(float) map.getDouble("coordinateY"));
            editor.putFloat("angle",(float) map.getDouble("angle"));
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
