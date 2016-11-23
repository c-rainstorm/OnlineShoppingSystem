package com.groupnine.oss.seller.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Goods {
    private int goodsId;
    private int categoryId;
    private String registrationId;
    private String goodsName;
    private String goodsDescribe;
    private ArrayList<GoodsImage> goodsImages = new ArrayList<>();
    private ArrayList<GoodsAttr> goodsAttrs = new ArrayList<>(20);
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

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
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

    public ArrayList<GoodsAttr> getGoodsAttrs() {
        return goodsAttrs;
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
