package com.groupnine.oss.user.entity;

import com.groupnine.oss.pub.entity.MalformedException;

public class UserFullInfo extends UserLoginInfo {
    private String nickname;
    private String avatar = "images/avatars/default.jpg";
    private String sex;
    private String birthday;

    // Constructor
    // 注意: 没有提供从 UserLoginInfo 直接构造 UserFullInfo，因为：
    // UserLoginInfo 中数据成员不完整，这种不完整导致的再次判断不如直接 set 来的方便

    public UserFullInfo() {
        super();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) throws MalformedException {
        if (Users.isNickNameWellFormed(nickname)) {
            this.nickname = nickname;
        } else
            throw new MalformedException("Malformed nickname");
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) throws MalformedException {
        this.birthday = birthday;
    }

}
