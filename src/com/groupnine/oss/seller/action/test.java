package com.groupnine.oss.seller.action;

import java.util.Calendar;

public class test {
    public static void main(String[] args) {
        // String str1 = "\"name\"";// 字符串两边含有双引号
        // String str2 = "name \"is\" wgb";// 字符串中间含有双引号
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.DAY_OF_YEAR, -(50));
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // String str = sdf.format(calendar.getTime());
        //
        java.util.Date dt = calendar.getTime();
        java.sql.Date date = new java.sql.Date(dt.getTime());

        System.out.print(date.toString());

        // String dd = "2012-10-1";
        //
        // Timestamp ts = new Timestamp(System.currentTimeMillis());
        // String tsStr = dd + " 00:00:00";
        // try {
        // ts = Timestamp.valueOf(tsStr);
        // System.out.println(ts);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        //
        // System.out.println(date);

        String result = "true";
        System.out.println(result);
    }
}
