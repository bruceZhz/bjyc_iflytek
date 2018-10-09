package com.cms.yancao.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cms.yancao.R;
import com.cms.yancao.common.AsrSingleton;
import com.cms.yancao.common.ViewHelper;

public class HomeFragment extends Fragment {
    private View mRootView;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        initEvent();
        return mRootView;
    }


    private void initEvent() {
        ViewHelper.setOnClickListener(mRootView, R.id.btn_law, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsrSingleton.getInstance().requestAnswer("法律法规");
            }
        });
    }
}
