package com.groupnine.oss.user.entity;

public class GoodsInOrder {
    public GoodsInOrderInfo goods;
    public String attributeValue;
    public String goodsNum;
    public String actualPrice;

    // constructor

    public GoodsInOrder(GoodsInOrderInfo i, String attrValue, String num, String price) {
        goods = i;
        attributeValue = attrValue;
        goodsNum = num;
        actualPrice = price;
    }
}
