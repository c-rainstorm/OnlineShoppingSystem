package com.groupnine.oss.seller.entity;

public class GoodsImage {
    private int imageId;
    private int goodsId;
    private String imageAddr;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getImageAddr() {
        return imageAddr;
    }

    public void setImageAddr(String imageAddr) {
        this.imageAddr = imageAddr;
    }
}
