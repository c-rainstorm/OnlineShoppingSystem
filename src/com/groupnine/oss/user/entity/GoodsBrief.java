package com.groupnine.oss.user.entity;

public class GoodsBrief {
    private String goodsId;
    private String attributeId;
    private String goodsName;
    private String goodsDescribe;
    private String imageAddr;
    private String sales;
    private String price;

    // Getters

    public String getGoodsId() {
        return goodsId;
    }

    public String getPrice() {
        return price;
    }

    // Setters

    public GoodsBrief setGoodsId(String id) {
        goodsId = id;
        return this;
    }

    public GoodsBrief setAttributeId(String id) {
        attributeId = id;
        return this;
    }

    public GoodsBrief setGoodsName(String name) {
        goodsName = name;
        return this;
    }

    public GoodsBrief setGoodsDescribe(String des) {
        goodsDescribe = des;
        return this;
    }

    public GoodsBrief setImageAddr(String addr) {
        imageAddr = addr;
        return this;
    }

    public GoodsBrief setSales(String n) {
        sales = n;
        return this;
    }

    public GoodsBrief setPrice(String p) {
        price = p;
        return this;
    }
}
