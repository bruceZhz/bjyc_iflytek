package com.binninfo.tobacco.mapper;

import java.util.*;

import com.binninfo.tobacco.entity.Goods;
import com.binninfo.tobacco.entity.SaleInfo;
import org.apache.ibatis.annotations.*;

public interface GoodsInfoMapper {

    @Select("select * from goodsInfo")
    List<Goods> getGoodsInfo();

    @Delete("delete from goodsInfo where id = #{id}")
    boolean delGoodsInfo(@Param("id") String id);


    @Insert(" insert into goodsInfo(goodsType,name,price,taste,address,goodsDegree,detail,goodsNumber,audience,haveBox,Recommend,imgURL) " +
             " VALUES( #{goodsType}, #{name}, #{price}, #{taste}, #{address}, #{goodsDegree}, #{detail}, #{goodsNumber}, #{audience}, #{haveBox}, #{Recommend}, #{imgURL})")
    boolean saveGoodsInfo(Goods goods);

    @Update(" UPDATE goodsInfo set name=#{name},price=#{price},taste=#{taste},address=#{address},goodsDegree=#{goodsDegree},detail=#{detail},goodsNumber=#{goodsNumber},audience=#{audience},haveBox=#{haveBox},Recommend=#{Recommend},imgURL=#{imgURL},goodsType=#{goodsType} " +
            " where id=#{id}")
    boolean updateGoodsInfo(Goods goods);

    @Select("select * from saleInfo where goodsID=#{goodsID}")
    SaleInfo getSaleInfo(@Param("goodsID")Integer goodsId);

    @Insert(" INSERT INTO saleInfo(saleDegree,content,goodsID) values(#{saleDegree},#{content},#{goodsID})")
    boolean saveSaleInfo(SaleInfo saleInfo);

    @Update("UPDATE saleInfo set saleDegree=#{saleDegree},content=#{content} where goodsID = #{goodsID}")
    boolean updateSaleInfo(SaleInfo saleInfo);

    @Update("update goodsInfo set imgURL =#{imgURL} where id = ${id}")
    boolean saveImg(@Param("id")String goodsID,@Param("imgURL") String imgURL);

    @Select("select * from goodsInfo where id = #{id}")
    Goods getGoodsInfoByID(@Param("id")String goodsID);



    @Select("<script>select * from goodsInfo" +
            " where 1=1  " +
            " <if test=\"goodsType !=null \">and goodsType = #{goodsType} </if>" +
            " <if test=\"address !=null \">and address = #{address} </if>" +
            " </script> ")
    public List<Goods> searchGoodsInfo(Goods goods);
}
