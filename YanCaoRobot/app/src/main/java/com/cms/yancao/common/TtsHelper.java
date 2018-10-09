package com.cms.yancao.common;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.cms.yancao.R;
import com.iflytek.aipsdk.common.InitListener;
import com.iflytek.aipsdk.tts.SpeechSynthesizer;
import com.iflytek.aipsdk.tts.SynthesizerListener;
import com.iflytek.aipsdk.util.SpeechError;
import com.iflytek.util.Logs;

public class TtsHelper implements InitListener {
    private static final String TAG = "TtsHelper";

    private SpeechSynthesizer mTts;

    public TtsHelper(Context context) {
        mTts = new SpeechSynthesizer(context.getApplicationContext(), this);
        setParams(context);
    }

    private void setParams(Context context) {
        String params = context.getResources().getString(R.string.tts_params).trim();
        mTts.setNewParams(params);
    }


    public void speak(String msg) {
        speak(msg, null);
    }

    public void speak(String msg, final Callback callback) {
        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
        mTts.startSpeaking(msg, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError error) {
                Log.e(TAG, "onCompleted-->error:" + error);
                if (callback != null) {
                    callback.onCompleted();
                }
            }

            @Override
            public void onBufferCompleted(String s, int i) {

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }

            @Override
            public void onPreError(int i) {

            }
        });
    }

    public boolean isSpeaking() {
        return mTts.isSpeaking();
    }

    public void stopSpeak() {
        mTts.stopSpeaking();
    }

    public void destroy(){
        mTts.stopSpeaking();
        mTts.destroy();
    }

    @Override
    public void onInit(int i) {

    }

    public interface Callback {
        void onCompleted();
    }
}
