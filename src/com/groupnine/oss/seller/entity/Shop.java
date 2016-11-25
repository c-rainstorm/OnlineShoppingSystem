package com.groupnine.oss.seller.entity;

public class Shop {
    private String registrationId;
    private int userId;
    private String shopName;
    private String address;
    private String phone;
    private String shopDescribe;
    private String announcement;
    private long evaluateSum;
    private long evaluateNumber;

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
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

    public long getEvaluateNumber() {
        return evaluateNumber;
    }

    public void setEvaluateNumber(long evaluateNumber) {
        this.evaluateNumber = evaluateNumber;
    }

    public long getEvaluateSum() {
        return evaluateSum;
    }

    public void setEvaluateSum(long evaluateSum) {
        this.evaluateSum = evaluateSum;
    }

}
