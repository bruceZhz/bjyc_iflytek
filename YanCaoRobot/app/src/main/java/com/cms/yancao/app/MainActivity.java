package com.cms.yancao.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.cms.yancao.R;
import com.cms.yancao.common.AsrSingleton;
import com.cms.yancao.common.Config;
import com.cms.yancao.common.HttpModel;
import com.cms.yancao.common.RobotControl;
import com.cms.yancao.entity.VideoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    private WakeFragment mWakeFragment;
    private HomeFragment mHomeFragment;
    private AnswerFragment mAnswerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        mFragmentManager = getSupportFragmentManager();

        switchFragment(buildHomeFragment());
        switchFragment(buildAnswerFragment());
        switchFragment(buildWakeFragment());

        AsrSingleton.getInstance().init(this, new AsrSingleton.Callback() {
            @Override
            public void onResult(int type, JSONObject obj) {
                showAnswer(type, obj);
            }
        });
        loadConfig();
    }

    private void switchFragment(Fragment target) {
        if (mCurrentFragment == target) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (!target.isAdded()) {
            transaction.add(R.id.fm_container, target, target.getClass().getSimpleName());
        } else {
            transaction.show(target);
        }
        transaction.commit();
        mCurrentFragment = target;
    }

    private WakeFragment buildWakeFragment() {
        WakeFragment fragment = (WakeFragment) mFragmentManager
                .findFragmentByTag(WakeFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = WakeFragment.newInstance();
        }
        return fragment;
    }

    private HomeFragment buildHomeFragment() {
        HomeFragment fragment = (HomeFragment) mFragmentManager
                .findFragmentByTag(HomeFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = HomeFragment.newInstance();
        }
        return fragment;
    }

    private AnswerFragment buildAnswerFragment() {
        AnswerFragment fragment = (AnswerFragment) mFragmentManager
                .findFragmentByTag(AnswerFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = AnswerFragment.newInstance();
        }
        return fragment;
    }


    private void showAnswer(int type, JSONObject obj) {
        switch (type) {
            case 1:
            case 3:
            case 4:
                mAnswerFragment = buildAnswerFragment();
                switchFragment(mAnswerFragment);
                mAnswerFragment.showAnswer(type, obj);
                break;
            case 2:
                AsrSingleton.getInstance().stopRecognizer();
                AsrSingleton.getInstance().setVideoPlaying(true);
                VideoActivity.start(this, HttpModel.prefix+obj.optString("vidoURL"));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onVideoCompletion(VideoEvent event) {
        AsrSingleton.getInstance().setVideoPlaying(false);
        AsrSingleton.getInstance().startRecognizer();
    }


    public void showHomeFragment() {
        switchFragment(mHomeFragment = buildHomeFragment());
    }

    public void showWakeFragment() {
        switchFragment(mWakeFragment = buildWakeFragment());
    }

    private void loadConfig() {
        final String robotId = RobotControl.getRobotGuid();
        Disposable d = HttpModel.loadConfig(robotId).subscribe(new Consumer<JSONObject>() {
            @Override
            public void accept(JSONObject obj) throws Exception {
                Log.e(TAG, "Config:" + obj.toString());
                Config.putConfig(obj);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable e) throws Exception {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AsrSingleton.getInstance().unInit();
        EventBus.getDefault().unregister(this);
    }
}
