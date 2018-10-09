package com.binninfo.tobacco.mapper;

import com.binninfo.tobacco.entity.Advertisement;
import com.binninfo.tobacco.entity.Goods;
import com.binninfo.tobacco.entity.SaleInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface AdvertisementMapper {
    @Select("select * from advertisement")
    List<Advertisement> getAdvertisementInfo();
    @Delete("delete from advertisement where id = #{id}")
    boolean delAdvertisementInfo(@Param("id") String id);
    @Update("update advertisement set title=#{title},content=#{content} where id =#{id}")
    boolean updateAdvertisementInfo(Advertisement Advertisement);
    @Insert("insert into advertisement(title,content) values(#{title},#{content})")
    boolean insertAdvertisementInfo(Advertisement Advertisement);
    @Update("update advertisement set vidoURL = #{vidoURL} where id=#{id}")
    boolean saveImg(@Param("id") String id,@Param("vidoURL") String vidoURL);
    @Select("select * from advertisement where id=#{id}")
    Advertisement getAdById(@Param("id") String id);
}
