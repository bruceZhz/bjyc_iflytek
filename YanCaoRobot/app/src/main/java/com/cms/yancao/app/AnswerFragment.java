package com.cms.yancao.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cms.yancao.R;
import com.cms.yancao.common.AsrSingleton;
import com.cms.yancao.common.HttpModel;
import com.cms.yancao.common.SpanHelper;
import com.cms.yancao.common.Utils;
import com.cms.yancao.common.ViewHelper;
import com.cms.yancao.common.adapter.CommonAdapter;
import com.cms.yancao.common.adapter.SpaceItemDecoration;
import com.cms.yancao.common.adapter.ViewHolder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnswerFragment extends Fragment {

    private Activity mActivity;
    private View mRootView;

    private ImageView mLogoImageView;
    private LinearLayout mGoodsLayout;
    private LinearLayout mCompanyLayout;
    private TextView mAnswerTextView;

    private LinearLayout mListLayout;
    private TextView mListTitleTextView;
    private RecyclerView mRecyclerView;
    private CommonAdapter<JSONObject> mAdapter;
    private List<JSONObject> mDataList = new ArrayList<>();

    public static AnswerFragment newInstance() {
        AnswerFragment fragment = new AnswerFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_answer, container, false);
        init();
        return mRootView;
    }

    private void init() {
        mLogoImageView = mRootView.findViewById(R.id.iv_logo);
        mGoodsLayout = mRootView.findViewById(R.id.ll_goods);
        mCompanyLayout = mRootView.findViewById(R.id.ll_company);
        mAnswerTextView = mRootView.findViewById(R.id.tv_answer_content);
        mListLayout = mRootView.findViewById(R.id.ll_list);
        mListTitleTextView = mRootView.findViewById(R.id.tv_list_title);
        mRecyclerView = mRootView.findViewById(R.id.rv_answer_list);
        mAdapter = new CommonAdapter<JSONObject>(mActivity, mDataList, R.layout.item_answer) {
            @Override
            public void convert(ViewHolder holder, JSONObject item) {
                final String title = item.optString("title");
                holder.setText(R.id.tv_title, title);
                holder.setOnClickListener(R.id.tv_title, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AsrSingleton.getInstance().requestAnswer(title);
                    }
                });
            }
        };
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(100, 30));
        mRecyclerView.setAdapter(mAdapter);
    }


    public void showAnswer(int type, JSONObject obj) {
        switch (type) {
            case 1:
                mLogoImageView.setImageResource(R.drawable.img_default);
                mCompanyLayout.setVisibility(View.VISIBLE);
                mGoodsLayout.setVisibility(View.GONE);
                mListLayout.setVisibility(View.GONE);
                mAnswerTextView.setVisibility(View.VISIBLE);
                mAnswerTextView.setText(obj.optString("answer"));
                break;
            case 3:
                showGoodsInfo(obj);
                break;
            case 4:
                mLogoImageView.setImageResource(R.drawable.img_default);
                mCompanyLayout.setVisibility(View.VISIBLE);
                mGoodsLayout.setVisibility(View.GONE);
                mListLayout.setVisibility(View.VISIBLE);
                mAnswerTextView.setVisibility(View.GONE);

                mDataList.clear();
                mDataList.addAll(Utils.convertJSONArray2List(obj.optJSONArray("list")));
                mAdapter.notifyDataSetChanged();
                mListTitleTextView.setText(obj.optString("answer"));
                break;
        }
    }

    private void showGoodsInfo(JSONObject obj) {
        JSONObject goods = obj.optJSONObject("goods");
        JSONArray imgs = goods.optJSONArray("imgList");
        if (imgs != null && imgs.length() > 0) {
            Picasso.with(mActivity).load(HttpModel.prefix + imgs.optString(0))
                    .error(R.drawable.img_default).into(mLogoImageView);
        } else {
            mLogoImageView.setImageResource(R.drawable.img_default);
        }

        mCompanyLayout.setVisibility(View.GONE);
        mGoodsLayout.setVisibility(View.VISIBLE);
        ViewHelper.setText(mGoodsLayout, R.id.goods_name, "商品名称：" + goods.optString("name"));
        ViewHelper.setText(mGoodsLayout, R.id.goods_address, "商品产地：" + goods.optString("address"));

        JSONObject promotions = goods.optJSONObject("promotions");
        if (promotions != null && !TextUtils.isEmpty(promotions.optString("price"))) {
            String price1 = goods.optString("price");
            String price2 = promotions.optString("price");
            SpanHelper helper = new SpanHelper();
            helper.append("商品价格：").setSpan(price2, new ForegroundColorSpan(Color.RED))
                    .append("  ").setSpan(price1, new StrikethroughSpan());
            ViewHelper.setText(mGoodsLayout, R.id.goods_price, helper.builder());
        } else {
            ViewHelper.setText(mGoodsLayout, R.id.goods_price, "商品价格：" + goods.optString("price"));
        }
        if (promotions != null && !TextUtils.isEmpty(promotions.optString("content"))) {
            ViewHelper.setVisibility(mGoodsLayout, R.id.goods_promotions, View.VISIBLE);
            ViewHelper.setText(mGoodsLayout, R.id.goods_promotions, promotions.optString("content"));
        } else {
            ViewHelper.setVisibility(mGoodsLayout, R.id.goods_promotions, View.GONE);
        }

        mListLayout.setVisibility(View.GONE);
        mAnswerTextView.setVisibility(View.VISIBLE);
        mAnswerTextView.setText(goods.optString("detail"));
    }
}
