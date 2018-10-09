package com.binninfo.tobacco.controller;

import com.binninfo.tobacco.entity.RobotInfo;
import com.binninfo.tobacco.entity.answerEntity.AnswerBean;
import com.binninfo.tobacco.service.INlpService;
import com.binninfo.tobacco.service.IRobotInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/robot")
public class RobotController {
    @Autowired
    INlpService nlpService;

    @Autowired
    IRobotInfoService robotInfoService;

    @Value("${nlpAddress}")
    String nlpUrl;

    @RequestMapping(value = "/getAnswer")
    @ResponseBody
    public AnswerBean getAnswer(String sourceTerminal, String content, String iss_uid, String type){
        return nlpService.getAnswer(sourceTerminal,content,iss_uid,type,nlpUrl);

    }
    @RequestMapping(value = "/getRobotInfoByRobotID")
    @ResponseBody
    public RobotInfo getInfo(String robotID){
        return robotInfoService.getRobotInfoByRobotID(robotID);
    }


}
