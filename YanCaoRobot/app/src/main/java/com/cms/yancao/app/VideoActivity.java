package com.cms.yancao.app;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.cms.yancao.R;
import com.cms.yancao.entity.VideoEvent;

import org.greenrobot.eventbus.EventBus;

public class VideoActivity extends BaseActivity {
    private VideoView mVideoPlayer;
    private ProgressBar mProgressBar;
    private String mVideoUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_video);
        mVideoPlayer = findViewById(R.id.video_player);
        mProgressBar = findViewById(R.id.video_loading);

        mVideoUrl = getIntent().getStringExtra("url");
        startPlay(mVideoUrl);
    }

    private void startPlay(String url) {
        mProgressBar.setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(url);
        //设置视频控制器
        mVideoPlayer.setMediaController(new MediaController(this));
        //播放完成回调
        mVideoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
        //缓冲完成回调
        mVideoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
        //设置视频路径
        mVideoPlayer.setVideoURI(uri);
        //开始播放视频
        mVideoPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new VideoEvent());
    }

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
