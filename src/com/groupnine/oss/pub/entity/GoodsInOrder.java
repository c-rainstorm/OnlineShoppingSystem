package com.groupnine.oss.pub.entity;

import java.sql.Timestamp;

import com.groupnine.oss.seller.entity.Goods;

public class GoodsInOrder {
    private String orderId;             // 16 位数字,含前导 0
    private Goods goods;
    private int attributeId;
    private String attributeValue;
    private int goodsNum;
    private double cost;
    private double actualPrice;
    private String comment;
    private short evaluateSocre;
    private Timestamp evaluateTime;

    public long getOrderId() {
        return Long.parseLong(orderId);
    }

    public void setOrderId(long orderId) {
        StringBuilder builder = new StringBuilder(20);
        builder.append("0000000000000000");
        String temp = Long.toString(orderId);
        builder.insert(builder.length() - temp.length() + 1, temp);
        this.orderId = builder.toString();
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public short getEvaluateSocre() {
        return evaluateSocre;
    }

    public void setEvaluateSocre(short evaluateSocre) {
        this.evaluateSocre = evaluateSocre;
    }

    public Timestamp getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Timestamp evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

}
