package com.binninfo.tobacco.controller;

import com.binninfo.tobacco.entity.Map;
import com.binninfo.tobacco.entity.Message;
import com.binninfo.tobacco.entity.SaleInfo;
import com.binninfo.tobacco.service.IMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/map")
public class MapController {
    @Autowired
    IMapService mapService;

    @RequestMapping(value = "/show")
    public String show(){
        return "robotInfo/mapInfo";
    }

    @RequestMapping(value = "/getMapInfo")
    @ResponseBody
    public List<Map> getMapInfo(){
        return mapService.getMapInfo();
    }


    @RequestMapping(value = "/delMapInfo")
    @ResponseBody
    public Message delMapInfo(String ids){
        Message res = new Message();
        if(mapService.delMapInfo(ids.split(","))){
            res.setContent("删除地图信息成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/saveMapInfo")
    @ResponseBody
    public Message saveMapInfo(Map MapInfo){
        Message res = new Message();
        if(mapService.insertMapInfo(MapInfo)){
            res.setContent("添加地图信息成功");
            res.setSuccess(true);
        }else{
            res.setContent("添加地图信息失败");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping(value = "/updateMapInfo")
    @ResponseBody
    public Message updateMapInfo(Map MapInfo){
        Message res = new Message();
        if(mapService.updateMapInfo(MapInfo)){
            res.setContent("地图信息更新成功");
            res.setSuccess(true);
        }else{
            res.setContent("地图信息更新失败");
            res.setSuccess(true);
        }
        return res;
    }



    @RequestMapping(value = "/updateMapImg")
    @ResponseBody
    public Message updateMapImg(String MapID,String imgURL){
        Message res = new Message();
        if(mapService.saveImg(MapID,imgURL)){
            res.setContent("设置地图图片成功");
            res.setSuccess(true);
        }else{
            res.setContent("设置地图图片失败");
            res.setSuccess(true);
        }
        return res;
    }
}
