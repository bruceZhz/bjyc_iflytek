package com.cms.yancao.common;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void showTip(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


    public static List<JSONObject> convertJSONArray2List(JSONArray array) {
        List<JSONObject> list = new ArrayList<>();
        if (array != null && array.length() > 0) {
            for (int i = 0; i < array.length(); i++) {
                list.add(array.optJSONObject(i));
            }
        }
        return list;
    }

    public static boolean isEmpty(CharSequence text) {
        return TextUtils.isEmpty(text) || text.equals("null");
    }
}
