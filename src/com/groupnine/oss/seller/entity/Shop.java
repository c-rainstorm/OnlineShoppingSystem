package com.groupnine.oss.seller.entity;

public class Shop {
    private int shopId;
    private int userId;
    private String shopName;
    private String address;
    private String phone;
    private String shopDescribe;
    private String announcement;
    private String applyStatus;
    private long evaluateSum;
    private long evaluateNumber;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopDescribe() {
        return shopDescribe;
    }

    public void setShopDescribe(String shopDescribe) {
        this.shopDescribe = shopDescribe;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public long getEvaluateSum() {
        return evaluateSum;
    }

    public void setEvaluateSum(long evaluateSum) {
        this.evaluateSum = evaluateSum;
    }

    public long getEvaluateNumber() {
        return evaluateNumber;
    }

    public void setEvaluateNumber(long evaluateNumber) {
        this.evaluateNumber = evaluateNumber;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

}
