package com.groupnine.oss.user.entity;

public class UserSessionInfo {
    private String userId;
    private String nickname;
    private String avatarAddr;
    private String shopHasOpend;  // 或许应该叫做 shopStatus，因为它有三种状态
    private String shopId;

    // Constructor

    public UserSessionInfo() {
    }

    public UserSessionInfo(String id, String nickname, String addr, String shopHasOpend,
            String shopId) {
        this.userId = id;
        this.nickname = nickname;
        this.avatarAddr = addr;
        this.shopHasOpend = shopHasOpend;
        this.shopId = shopId;
    }

    // Getters

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatarAddr() {
        return avatarAddr;
    }

    public String isShopHasOpend() {
        return shopHasOpend;
    }

    public String getShopId() {
        return shopId;
    }

    // Setters

    public void setUserId(String id) {
        this.userId = id;
    }

    public void setNickname(String nn) {
        this.nickname = nn;
    }

    public void setAvatarAddr(String addr) {
        avatarAddr = addr;
    }

    public void setShopHasOpend(String opend) {
        this.shopHasOpend = opend;
    }

    public void setShopId(String id) {
        this.shopId = id;
    }
}
