package com.binninfo.tobacco.mapper;

import com.binninfo.tobacco.entity.RobotInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.*;

public interface RobotInfoMapper {
    @Select("select * from RobotInfo")
    List<RobotInfo> getRobot();

    @Delete("delete from RobotInfo where id=#{id}")
    boolean delRobot(@Param("id") String  id);

    @Insert("insert into RobotInfo(robotid,address,sleepTime,deniedWordOne,deniedWordTwo,ltNumber,gtNumber,overtimeWord,createTIme,welcome,location) " +
            " VALUES(#{robotid},#{address},#{sleepTime},#{deniedWordOne},#{deniedWordTwo},#{ltNumber},#{gtNumber},#{overtimeWord},#{welcome},#{location} now())")
    boolean saveRobot(RobotInfo robotInfo);



    @Insert("UPDATE RobotInfo set robotid =#{robotid},address = #{address},sleepTime=#{sleepTime},deniedWordOne=#{deniedWordOne},deniedWordTwo =#{deniedWordTwo}, ltNumber=#{ltNumber},gtNumber =#{gtNumber}, overtimeWord=#{overtimeWord},welcome=#{welcome},location=#{location} " +
            "where id =#{id} ")
    boolean updateRobot(RobotInfo robotInfo);

    @Select("select * from RobotInfo where robotid =#{robotid}")
    RobotInfo getRobotInfoByRobotID(@Param("robotid") String id);
}
