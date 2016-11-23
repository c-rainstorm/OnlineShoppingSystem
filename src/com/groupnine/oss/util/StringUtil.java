package com.groupnine.oss.util;

public class StringUtil {

    /**
     * 判断一个字符串内是否有值
     * 
     * @param str
     *            带判断字符串
     * @return false -- 字符串不为空 true -- 空串或null
     */
    public static boolean isEmpty(String str) {
        boolean flag = false;

        if (str == null || str.length() == 0) {
            flag = true;
        }

        return flag;
    }
}
