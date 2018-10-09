package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.Goods;
import com.binninfo.tobacco.entity.SaleInfo;

import java.util.*;

public interface IGoodsService {
    List<Goods> getGoodsInfo();
    boolean delGoodsInfo(String[] ids);
    boolean updateGoodsInfo(Goods goods);
    boolean insertGoodsInfo(Goods goods);
    SaleInfo getSaleInfo(Integer goodID);
    boolean saveSaleInfo(SaleInfo saleInfo);
    boolean updateSaleInfo(SaleInfo saleInfo);
    boolean saveImg(String goodsID,String imgURL);
}
