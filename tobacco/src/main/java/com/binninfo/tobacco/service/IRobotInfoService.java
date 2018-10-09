package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.RobotInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import java.util.*;


public interface IRobotInfoService {
    PageInfo<RobotInfo> getRobot(int page, int rows);
    boolean delRobot(String[] ids);
    boolean saveRobot(RobotInfo robotInfo);
    boolean updateRobot(RobotInfo robotInfo);
    RobotInfo getRobotInfoByRobotID(String id);
}
