package com.cms.yancao.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.android.iflytek.IflytekManager;

public class WakeUpHelper {

    private Context mContext;
    private WakeUpListener mListener;
    private BroadcastReceiver mWakeUpReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (IflytekManager.ACTION_AIUI_WAKEUP.equals(intent.getAction())) {
                // 获取唤醒角度
                int angle = intent.getIntExtra(IflytekManager.EXTRA_AIUI_SOUND_SOURCE_ANGLE, -1);
                if (angle != -1 && mListener != null) {
                    mListener.onWakeUp(0);
                }
            }
        }
    };

    public WakeUpHelper(Context context) {
        mContext = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IflytekManager.ACTION_AIUI_WAKEUP);
        mContext.registerReceiver(mWakeUpReceiver, intentFilter);
    }

    public void unregister() {
        mContext.unregisterReceiver(mWakeUpReceiver);
    }


    public void setWakeUpListener(WakeUpListener listener) {
        mListener = listener;
    }


    public interface WakeUpListener {
        void onWakeUp(int angle);
    }
}
