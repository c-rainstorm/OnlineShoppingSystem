package com.groupnine.oss.user.entity;

public class GoodsInOrderInfo {
    public String goodsId;
    public String goodsName;
    public String goodsDescribe;
    public String imageAddr;

    // Constructor

    public GoodsInOrderInfo(String id, String name, String desc, String addr) {
        goodsId = id;
        goodsName = name;
        goodsDescribe = desc;
        imageAddr = addr;
    }
}
