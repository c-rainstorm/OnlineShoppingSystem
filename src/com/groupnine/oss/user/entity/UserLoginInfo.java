package com.groupnine.oss.user.entity;

import com.groupnine.oss.pub.entity.MalformedException;

public class UserLoginInfo {
    private String username;
    private String phone;
    private String password;

    // Constructor

    public UserLoginInfo(String username, String phone, String password)
            throws MalformedException {

        /* 1. 如果给出的 username 和 phone 都格式不正确，或者密码格式不正确，抛异常 */

        if (!Users.isPasswordWellFormed(password))
            throw new MalformedException("Malformed login info");
        if (!(Users.isUsernameWellFormed(username) || Users.isPhoneWellFormed(phone))) {
            throw new MalformedException("Malformed login info");
        }

        /* 2. 存储 */
        this.username = username;
        this.phone = phone;
        this.password = password;
    }

    protected UserLoginInfo() {
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws MalformedException {
        if (Users.isUsernameWellFormed(username)) {
            this.username = username;
        } else
            throw new MalformedException("Malformed username");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws MalformedException {
        if (Users.isPhoneWellFormed(phone)) {
            this.phone = phone;
        } else
            throw new MalformedException("Malformed phone");
    }
}
