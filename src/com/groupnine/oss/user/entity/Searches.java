package com.groupnine.oss.user.entity;

public class Searches {
    public static boolean isKeywordWellFormed(String keyword) {
        return (keyword != null) &&
                (keyword.trim().length() > 0 && keyword.trim().length() < 32);
    }

    public static boolean isMaxNumInOnePageWF(String n) {
        boolean wf = false;
        try {
            if ((n != null) && (Integer.valueOf(n) >= 0))
                wf = true;
        } catch (Exception e) {
            // 当异常发生时，意味着 n 不是一个数字
        }

        return wf;
    }

    public static boolean isCurrentPageWF(String n) {
        boolean wf = false;
        try {
            if ((n != null) && Integer.valueOf(n) >= 0)
                wf = true;
        } catch (Exception e) {
            // 当异常发生时，意味着 n 不是一个数字
        }

        return wf;
    }

    public static boolean isSortMethodWF(String s) {
        return (s != null) &&
                (s.equals("true") || s.equals("false"));
    }
}
