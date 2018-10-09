package com.binninfo.tobacco.entity;

import java.util.*;

public class Goods {
    private Integer id;
    private String goodsType;
    private String name;
    private Double price;
    private String taste;
    private String address;
    private String goodsDegree;
    private String detail;
    private Integer goodsNumber;
    private String audience;
    private Integer haveBox;
    private String Recommend;
    private String imgURL;
    private SaleInfo promotions;

    private List<String> imgList = new ArrayList<>();

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public SaleInfo getPromotions() {
        return promotions;
    }

    public void setPromotions(SaleInfo promotions) {
        this.promotions = promotions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoodsDegree() {
        return goodsDegree;
    }

    public void setGoodsDegree(String goodsDegree) {
        this.goodsDegree = goodsDegree;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public Integer getHaveBox() {
        return haveBox;
    }

    public void setHaveBox(Integer haveBox) {
        this.haveBox = haveBox;
    }

    public String getRecommend() {
        return Recommend;
    }

    public void setRecommend(String recommend) {
        Recommend = recommend;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        imgList.add(imgURL);
        this.imgURL = imgURL;
    }
}
