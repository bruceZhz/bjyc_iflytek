package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.Goods;
import com.binninfo.tobacco.entity.SaleInfo;
import com.binninfo.tobacco.mapper.GoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService implements IGoodsService {
    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<Goods> getGoodsInfo() {
        return goodsInfoMapper.getGoodsInfo();
    }

    @Override
    public boolean delGoodsInfo(String[] ids) {
        for(int i = 0; i<ids.length; i++){
            goodsInfoMapper.delGoodsInfo(ids[i]);
        }
        return true;
    }

    @Override
    public boolean updateGoodsInfo(Goods goods) {
        return goodsInfoMapper.updateGoodsInfo(goods);
    }

    @Override
    public boolean insertGoodsInfo(Goods goods) {
        return goodsInfoMapper.saveGoodsInfo(goods);
    }

    @Override
    public SaleInfo getSaleInfo(Integer goodID) {
        return goodsInfoMapper.getSaleInfo(goodID);
    }

    @Override
    public boolean saveSaleInfo(SaleInfo saleInfo) {
        return goodsInfoMapper.saveSaleInfo(saleInfo);
    }

    @Override
    public boolean updateSaleInfo(SaleInfo saleInfo) {
        return goodsInfoMapper.updateSaleInfo(saleInfo);
    }

    @Override
    public boolean saveImg(String goodsID, String imgURL) {
        return goodsInfoMapper.saveImg(goodsID,imgURL);
    }
}
