package com.groupnine.oss.seller.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Goods {
    private int goodsId;
    private int categoryId;
    private int shopId;
    private String goodsName;
    private String goodsDescribe;
    private ArrayList<GoodsImage> goodsImages;
    private ArrayList<GoodsAttr> goodsAttrs;
    private int sales;
    private Timestamp discountDeadline;
    private double discountRate;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
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

    public ArrayList<GoodsImage> getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(ArrayList<GoodsImage> goodsImages) {
        this.goodsImages = goodsImages;
    }

    public ArrayList<GoodsAttr> getGoodsAttrs() {
        return goodsAttrs;
    }

    public void setGoodsAttrs(ArrayList<GoodsAttr> goodsAttrs) {
        this.goodsAttrs = goodsAttrs;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public Timestamp getDiscountDeadline() {
        return discountDeadline;
    }

    public void setDiscountDeadline(Timestamp discountDeadline) {
        this.discountDeadline = discountDeadline;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
