package com.groupnine.oss.user.entity;

import com.groupnine.oss.pub.entity.MalformedException;

public class GoodsItemInSC {
    private String id;
    private String goodsId;
    private GoodsItemInfo goods;
    private String attributeId;
    private String goodsNum;

    // Default empty constructor

    // Getters

    public String getId() {
        return id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public GoodsItemInfo getGoodsInfo() {
        return goods;
    }

    // Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setGoodsId(String id) throws MalformedException {
        if (Goods.isGoodsIdWellFormed(id))
            goodsId = id;
        else
            throw new MalformedException();
    }

    public void setGoodsInfo(GoodsItemInfo i) {
        goods = i;
    }

    public void setAttributeId(String id) throws MalformedException {
        if (Goods.isGoodsAttributeIdWellFormed(id))
            attributeId = id;
        else
            throw new MalformedException();
    }

    public void setGoodsNum(String n) throws MalformedException {
        if (Goods.isGoodsNumWellFormed(n))
            goodsNum = n;
        else
            throw new MalformedException();
    }
}
