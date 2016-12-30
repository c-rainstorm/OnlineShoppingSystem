package com.groupnine.oss.user.entity;

import java.util.ArrayList;

public class GoodsItemInfo {
    private String goodsId;
    private String goodsName;
    private String goodsDescribe;
    private String imageAddr;
    private ArrayList<GoodsAttrString> goodsAttrs = new ArrayList<>();

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public String getImageAddr() {
        return imageAddr;
    }

    public void setImageAddr(String addr) {
        imageAddr = addr;
    }

    public ArrayList<GoodsAttrString> getGoodsAttrs() {
        return goodsAttrs;
    }

    public void addGoodsAttr(GoodsAttrString attr) {
        this.goodsAttrs.add(attr);
    }
}
