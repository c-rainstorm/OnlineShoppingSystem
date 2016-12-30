package com.groupnine.oss.user.entity;

public class Receiver {
    private String receiverId;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private String usedTimes;

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getName() {
        return receiverName;
    }

    public void setName(String name) {
        this.receiverName = name;
    }

    public String getAddress() {
        return receiverAddress;
    }

    public void setAddress(String address) {
        this.receiverAddress = address;
    }

    public String getPhone() {
        return receiverPhone;
    }

    public void setPhone(String phone) {
        this.receiverPhone = phone;
    }

    public String getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(String usedTimes) {
        this.usedTimes = usedTimes;
    }

}
