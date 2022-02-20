package com.gvssimux.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UserVerify {

    /*****************↓↓↓封装的方法↓↓↓********************/

    /**
     * 判断字符串是否以字母或_开头
     * @param str 待检验的字符串
     * @return 返回是否包含
     * true  以字母或_开头 ; false  不以字母或_开头
     */
    public static boolean judgeContainsStr(String str) {
        String regex="[a-zA-Z]+.*";
        Matcher m= Pattern.compile(regex).matcher(str);
        if(m.matches()==false){
            return str.startsWith("_");
        }
        return m.matches();
    }

    /**
     * 判断字符串是否包含字母
     * @param str 待检验的字符串
     * @return 返回是否包含
     * true: 是 ;false 否
     */
    public static boolean judgeContainsStr2(String str) {
        String regex=".+[a-zA-Z]+.*";
        Matcher m= Pattern.compile(regex).matcher(str);

        return m.matches();
    }


    /**
     * 判断一个字符串是否都为数字
     * @param str 待检验的字符串
     * @return
     * true: 是 ;false 否
     */
    public static boolean isDigit(String str) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) str);
        return matcher.matches();
    }



    /**
     * 判断一个字符串是否含有数字
     * @param str 待检验的字符串
     * @return
     * true: 是 ;false 否
     */
    public static boolean HasDigit(String str) {
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(str);

        return m.matches();
    }



    /**
     * 字符串的长度是否 a<=str<=b
     * @param str 待检验的字符串
     * @return 返回是否
     * true: 是 ;false 否
     */
    public static boolean isStrLength(String str,int a,int b) {
        if (a<=str.length()&&str.length()<=b){
            return true;
        }
        return false;
    }

    /***************↑↑↑封装的方法↑↑↑*************************/



}
