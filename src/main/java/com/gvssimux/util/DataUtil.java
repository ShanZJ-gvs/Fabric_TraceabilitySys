package com.gvssimux.util;

import org.junit.Test;

import java.util.ArrayList;

public class DataUtil {

    static String[] reList = {"","一月", "二月", "三月", "四月", "五月", "六月","七月","八月","九月","十月","十一月","十二月"};
    /*"2022-04-14-20-24-19"*/
    public static String getMouth(String datatime){
        char c = datatime.charAt(5);
        char b = datatime.charAt(6);
        String s = String.valueOf(c).concat(String.valueOf(b));
        System.out.println("获取月份===》"+s);
        String s1 = reList[Integer.parseInt(s)];
        System.out.println("解析月份===》"+s1);
        return s1;
    }

    @Test
    public void test01(){
        String[] reList = {"","一月", "二月", "三月", "四月", "五月", "六月","七月","八月","九月","十月","十一月","十二月"};
        String mouth = getMouth("2022-12-14-20-24-19");
        System.out.println(mouth);
    }

}
