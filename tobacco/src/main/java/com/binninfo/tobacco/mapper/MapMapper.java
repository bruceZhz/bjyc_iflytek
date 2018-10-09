package com.binninfo.tobacco.mapper;

import com.binninfo.tobacco.entity.Map;
import com.binninfo.tobacco.entity.SaleInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MapMapper {
    @Delete("delete from MapInfo where id = #{id}")
    boolean delMapInfo(@Param("id") String id);

    @Insert(" insert into MapInfo(target,coordinateX,coordinateY,angle,mapIMG) values(#{target},#{coordinateX},#{coordinateY},#{angle},#{mapIMG})")
    boolean saveMapInfo(Map Map);

    @Update(" UPDATE MapInfo set target=#{target},coordinateX=#{coordinateX},coordinateY=#{coordinateY},angle=#{angle},mapIMG=#{mapIMG} " +
            " where id=#{id}")
    boolean updateMapInfo(Map Map);

    @Update("update MapInfo set mapIMG =#{mapIMG} where id = ${id}")
    boolean saveImg(@Param("id")String MapID,@Param("mapIMG") String imgURL);

    @Select("select * from MapInfo where id = #{id}")
    Map getMapInfoByID(@Param("id")String MapID);

    @Select("select * from MapInfo")
    List<Map> getMaps();

}
