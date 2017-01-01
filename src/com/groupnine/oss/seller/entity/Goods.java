package com.groupnine.oss.seller.entity;

import java.util.ArrayList;

public class Goods {
    private int goodsId;
    private int categoryId;
    private String firstCategory;
    private String secondCategory;
    private int shopId;
    private String goodsName;
    private String goodsDescribe;
    private ArrayList<String> goodsImagesUrl;
    private ArrayList<GoodsImage> goodsImages;
    private ArrayList<GoodsAttr> goodsAttrs;
    private int sales;
    private String discountDeadline;
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

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
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

    public ArrayList<String> getGoodsImagesUrl() {
        return goodsImagesUrl;
    }

    public void setGoodsImagesUrl(ArrayList<String> goodsImagesUrl) {
        this.goodsImagesUrl = goodsImagesUrl;
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

    public String getDiscountDeadline() {
        return discountDeadline;
    }

    public void setDiscountDeadline(String discountDeadline) {
        this.discountDeadline = discountDeadline;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
