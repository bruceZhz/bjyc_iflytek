package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.RobotInfo;
import com.binninfo.tobacco.mapper.RobotInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotInfoService implements IRobotInfoService {
    @Autowired
    RobotInfoMapper robotInfoMapper;

    @Override
    public PageInfo<RobotInfo> getRobot(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<RobotInfo> robotInfos = robotInfoMapper.getRobot();
        PageInfo<RobotInfo> robotInfoPageInfo = new PageInfo<>(robotInfos);
        return robotInfoPageInfo;
    }

    @Override
    public boolean delRobot(String[] ids) {
        for(int i=0 ;i<ids.length ; i++){
            robotInfoMapper.delRobot(ids[i]);
        }
        return true;
    }

    @Override
    public boolean saveRobot(RobotInfo robotInfo) {
        return robotInfoMapper.saveRobot(robotInfo);
    }

    @Override
    public boolean updateRobot(RobotInfo robotInfo) {

        return robotInfoMapper.updateRobot(robotInfo);
    }

    @Override
    public RobotInfo getRobotInfoByRobotID(String id) {
        return robotInfoMapper.getRobotInfoByRobotID(id);
    }

}
