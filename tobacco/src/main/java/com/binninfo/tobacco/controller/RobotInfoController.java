package com.binninfo.tobacco.controller;

import com.alibaba.fastjson.JSONObject;
import com.binninfo.tobacco.entity.DataGrid;
import com.binninfo.tobacco.entity.Message;
import com.binninfo.tobacco.entity.RobotInfo;

import com.binninfo.tobacco.service.IRobotInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/robotInfo")
public class RobotInfoController {
    @Autowired
    IRobotInfoService robotInfoService;
    @RequestMapping(value="/show")
    public String show(){
        return "robotInfo/show";
    }

    @RequestMapping(value = "/getRobotInfo")
    @ResponseBody
    public DataGrid getRobotInfo(int page, int rows){
        return new DataGrid<RobotInfo>(robotInfoService.getRobot(page,rows));
    }

    @RequestMapping(value = "/delRobotInfo")
    @ResponseBody
    public Message delRobotInfo(String ids){
        Message res = new Message();
        if(robotInfoService.delRobot(ids.split(","))){
            res.setContent("删除机器人信息成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/saveRobotInfo")
    @ResponseBody
    public Message saveRobotInfo(RobotInfo robotInfo){
        Message res = new Message();
        if(robotInfoService.saveRobot(robotInfo)){
            res.setContent("添加机器人信息成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/updateRobotInfo")
    @ResponseBody
    public Message updateRobotInfo(RobotInfo robotInfo){
        Message res = new Message();
        if(robotInfoService.updateRobot(robotInfo)){
            res.setContent("机器人信息更新成功");
            res.setSuccess(true);
        }
        return res;
    }




}
