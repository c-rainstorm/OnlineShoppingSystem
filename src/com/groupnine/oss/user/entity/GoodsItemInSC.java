package com.groupnine.oss.user.entity;

import com.groupnine.oss.pub.entity.MalformedException;

public class GoodsItemInSC {
    private String goodsId;
    private String attributeId;
    private String goodsNum;

    // Getters

    public String getGoodsId() {
        return goodsId;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    // Setters

    public void setGoodsId(String id) throws MalformedException {
        if (id != null && id.matches("[0-9]+"))
            goodsId = id;
        else
            throw new MalformedException();

    }

    public void setAttributeId(String id) throws MalformedException {
        if (id != null && id.matches("[0-9]+"))
            attributeId = id;
        else
            throw new MalformedException();
    }

    public void setGoodsNum(String n) throws MalformedException {
        if (n != null && n.matches("[1-9][0-9]*"))
            goodsNum = n;
        else
            throw new MalformedException();
    }
}
