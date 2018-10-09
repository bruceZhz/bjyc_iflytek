package com.cms.yancao.common.adapter;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int verticalSpacing;
    private int horizontalSpacing;

    public SpaceItemDecoration(int horizontalSpacing,int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
        this.horizontalSpacing = horizontalSpacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int spanCount = manager.getSpanCount();
            if (parent.getChildLayoutPosition(view) % spanCount == 0) {
                outRect.left = 0;
                outRect.bottom = verticalSpacing;
            }else {
                outRect.left = horizontalSpacing;
                outRect.bottom = verticalSpacing;
            }
        }
    }
}

