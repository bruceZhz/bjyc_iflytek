package com.binninfo.tobacco.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    private String login(){
        return "robotInfo/main";
    };

    @RequestMapping(value = "/getAuthority")
    @ResponseBody
    private String getAuthority(){
        JSONArray jsonArray = new JSONArray();
        JSONObject object1 = new JSONObject();
        object1.put("englishName","/goods/show");
        object1.put("resourceName","商品列表");
        object1.put("id","goods/show");
        jsonArray.add(object1);
        return  jsonArray.toString();
    }


}
