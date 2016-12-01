package com.groupnine.oss.user.entity;

import java.sql.Date;

public class Users {

    public static boolean isUsernameWellFormed(String name) {
        return (name != null) &&
                (name.trim().length() > 0 && name.trim().length() <= 25) &&
                (name.trim().matches("^[a-zA-Z][-0-9a-zA-Z_]*$"));
    }

    public static boolean isPhoneWellFormed(String phone) {
        return (phone != null) &&
                (phone.trim().matches("^1[0-9]{10}$"));

    }

    public static boolean isPasswordWellFormed(String passwd) {
        return (passwd != null) &&
                (passwd.trim().length() >= 6 && passwd.trim().length() <= 20) &&
                (passwd.trim().matches("^[-_a-zA-Z0-9]*$"));
    }

    public static boolean isNickNameWellFormed(String nick) {
        return (nick != null) &&
                (nick.trim().length() > 0 && nick.trim().length() <= 20);
    }

    public static boolean isSexWellFormed(String sex) {
        return (sex != null) &&
                (sex.trim().equals("男") || sex.trim().equals("女") || sex.trim().equals("保密"));
    }

    public static boolean isBirthdayWellFormed(Date birthday) {
        return false;
        // TODO
    }
}
