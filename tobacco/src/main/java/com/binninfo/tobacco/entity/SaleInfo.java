package com.binninfo.tobacco.entity;

public class SaleInfo {
    private Integer id;
    private Integer goodsID;
    private Double saleDegree;
    private String content;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Integer goodsID) {
        this.goodsID = goodsID;
    }

    public Double getSaleDegree() {
        return saleDegree;
    }

    public void setSaleDegree(Double saleDegree) {
        this.price = saleDegree;
        this.saleDegree = saleDegree;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
