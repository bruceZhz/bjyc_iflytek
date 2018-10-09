package com.binninfo.tobacco.controller;

import com.binninfo.tobacco.entity.Goods;
import com.binninfo.tobacco.entity.Message;
import com.binninfo.tobacco.entity.RobotInfo;
import com.binninfo.tobacco.entity.SaleInfo;
import com.binninfo.tobacco.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {
    @Autowired
    IGoodsService goodsService;


    @RequestMapping(value = "/show")
    public String show(){
        return "robotInfo/goodsInfo";
    }

    @RequestMapping(value = "/getGoodsInfo")
    @ResponseBody
    public List<Goods> getGoodsInfo(){
        return goodsService.getGoodsInfo();
    }


    @RequestMapping(value = "/delGoodsInfo")
    @ResponseBody
    public Message delGoodsInfo(String ids){
        Message res = new Message();
        if(goodsService.delGoodsInfo(ids.split(","))){
            res.setContent("删除商品信息成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/saveGoodsInfo")
    @ResponseBody
    public Message saveGoodsInfo(Goods GoodsInfo){
        Message res = new Message();
        if(goodsService.insertGoodsInfo(GoodsInfo)){
            res.setContent("添加商品信息成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/updateGoodsInfo")
    @ResponseBody
    public Message updateGoodsInfo(Goods GoodsInfo){
        Message res = new Message();
        if(goodsService.updateGoodsInfo(GoodsInfo)){
            res.setContent("商品信息更新成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/getSaleInfo")
    @ResponseBody
    public SaleInfo getSaleInfo(Integer goodsID){
        return  goodsService.getSaleInfo(goodsID);
    }

    @RequestMapping(value = "/saveSaleInfo")
    @ResponseBody
    public Message saveSaleInfo(SaleInfo saleInfo){
        Message res = new Message();
        if(goodsService.saveSaleInfo(saleInfo)){
            res.setContent("设置销售信息成功");
            res.setSuccess(true);
        }
        return res;
    }


    @RequestMapping(value = "/updateSaleInfo")
    @ResponseBody
    public Message updateSaleInfo(SaleInfo saleInfo){
        Message res = new Message();
        if(goodsService.updateSaleInfo(saleInfo)){
            res.setContent("设置销售信息成功");
            res.setSuccess(true);
        }
        return res;
    }

    @RequestMapping(value = "/updateGoodsImg")
    @ResponseBody
    public Message updateGoodsImg(String goodsID,String imgURL){
        Message res = new Message();
        if(goodsService.saveImg(goodsID,imgURL)){
            res.setContent("设置商品图片成功");
            res.setSuccess(true);
        }
        return res;
    }
}
