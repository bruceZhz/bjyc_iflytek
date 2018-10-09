package com.cms.yancao.common;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;

/**
 * Created by bin on 2018/3/29.
 */

public class SpanHelper {
    SpannableStringBuilder ssb = new SpannableStringBuilder();

    public SpanHelper append(CharSequence text) {
        ssb.append(text);
        return this;
    }

    public SpanHelper setSpan(CharSequence text, Object what) {
        int index = ssb.length();
        ssb.append(text);
        ssb.setSpan(what, index, index + text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableStringBuilder builder() {
        return ssb;
    }
}
