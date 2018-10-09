package com.cms.yancao.common;

import org.json.JSONArray;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class WeatherHelper {
    private String[] mWeatherKeys = new String[]{"今天天气", "明天天气", "后天天气"};
    private String[] mDays = new String[]{"今天", "明天", "后天"};


    public int isWeatherAnswer(String answer) {
        for (int i = 0; i < mWeatherKeys.length; i++) {
            if (mWeatherKeys[i].equals(answer)) {
                return i;
            }
        }
        return -1;
    }

    public Observable<String> loadWeather(final int index) {
        return HttpModel.loadWeather().map(new Function<JSONObject, String>() {
            @Override
            public String apply(JSONObject obj) throws Exception {
                String result = "";
                int status = obj.optInt("status");
                if (status == 200) {
                    JSONArray array = obj.optJSONObject("data").optJSONArray("forecast");
                    result = generateWeather(array, index);
                }
                return result;
            }
        });
    }

    private String generateWeather(JSONArray array, int index) {
        StringBuilder sb = new StringBuilder();
        JSONObject current = array.optJSONObject(index);
        sb.append(mDays[index]).append("是").append(current.optString("type"))
                .append("  ").append(current.optString("fx"))
                .append("  ").append(getTemperature(current))
                .append("  \n").append("温馨提示：").append(current.optString("notice"))
                .append("  \n");
        String speak = sb.toString().replace("温馨提示:", "。");
        speak = speak.replace("  ", ",");
        int size = array.length() > 3 ? 3 : array.length();
        for (int i = 0; i < size; i++) {
            JSONObject obj = array.optJSONObject(i);
            sb.append(mDays[i]).append("  ")
                    .append(getTemperature(obj))
                    .append("  ").append(obj.optString("type"))
                    .append("  ").append(obj.optString("fx")).append("  \n");
        }
        sb.deleteCharAt(sb.lastIndexOf("\n"));
        return sb.toString();
    }


    private String getTemperature(JSONObject obj) {
        String low = obj.optString("low");
        String high = obj.optString("high");
        return low.substring(3) + "/" + high.substring(3);
    }
}
