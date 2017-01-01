package com.groupnine.oss.user.entity;

import java.util.ArrayList;

public class GoodsDetail {
    public String goodsId;
    public String goodsName;
    public String goodsDescribe;
    public String discountDeadline;
    public String discountRate;
    public ArrayList<GoodsAttrString> attributes = new ArrayList<>();
    public ArrayList<String> images = new ArrayList<>();
    public String levelOne;
    public String levelTwo;

    // setters

    public GoodsDetail setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public GoodsDetail setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public GoodsDetail setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
        return this;
    }

    public GoodsDetail setDiscountDeadline(String discountDeadline) {
        this.discountDeadline = discountDeadline;
        return this;
    }

    public GoodsDetail setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public GoodsDetail addAttr(GoodsAttrString a) {
        attributes.add(a);
        return this;
    }

    public GoodsDetail addImage(String image) {
        images.add(image);
        return this;
    }

    public void setLevelOne(String levelOne) {
        this.levelOne = levelOne;
    }

    public void setLevelTwo(String levelTwo) {
        this.levelTwo = levelTwo;
    }
}
