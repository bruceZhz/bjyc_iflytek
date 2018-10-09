package com.binninfo.tobacco.controller;

import com.binninfo.tobacco.entity.Advertisement;
import com.binninfo.tobacco.entity.Message;
import com.binninfo.tobacco.entity.SaleInfo;
import com.binninfo.tobacco.service.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {
    @Autowired
    IAdvertisementService advertisementService;
    @RequestMapping(value = "/show")
    public String show(){
        return "robotInfo/advertisementInfo";
    }

    @RequestMapping(value = "/getAdvertisementInfo")
    @ResponseBody
    public List<Advertisement> getAdvertisementInfo(){
        return advertisementService.getAdvertisementInfo();
    }


    @RequestMapping(value = "/delAdvertisementInfo")
    @ResponseBody
    public Message delAdvertisementInfo(String ids){
        Message res = new Message();
        if(advertisementService.delAdvertisementInfo(ids.split(","))){
            res.setContent("删除宣传信息成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/saveAdvertisementInfo")
    @ResponseBody
    public Message saveAdvertisementInfo(Advertisement AdvertisementInfo){
        Message res = new Message();
        if(advertisementService.insertAdvertisementInfo(AdvertisementInfo)){
            res.setContent("添加宣传信息成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/updateAdvertisementInfo")
    @ResponseBody
    public Message updateAdvertisementInfo(Advertisement AdvertisementInfo){
        Message res = new Message();
        if(advertisementService.updateAdvertisementInfo(AdvertisementInfo)){
            res.setContent("宣传信息更新成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/updateAdvertisementImg")
    @ResponseBody
    public Message updateAdvertisementImg(String id,String vidoURL){
        Message res = new Message();
        if(advertisementService.saveImg(id,vidoURL)){
            res.setContent("设置宣传图片成功");
            res.setSuccess(true);
        }
        return res;
    }

}
