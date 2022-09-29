package com.yt.utils;

import com.yt.http.core.HttpSample;
import com.yt.http.core.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StrKit {

    public static void main(String[] args) {
        List<RequestParam> list = new ArrayList<>();
        list.add(new RequestParam("name","libo"));
        list.add(new RequestParam("sex","man"));
        list.add(new RequestParam("age","19"));
        list.add(new RequestParam("","180"));

        list = list.stream().filter(item->StrKit.isVariableNameRight(item.getName())).collect(Collectors.toList());
        System.out.println(list.stream().filter(item->item.getName().equals("name")).findFirst().orElse(null));
    }

    public static String trim(String str){
        if(!isEmpty(str)){
            return str.trim();
        }
        return str;
    }

    /**
     * 变量名是否合法
     * @param variableName
     * @return
     */
    public static boolean isVariableNameRight(String variableName){
        if(isEmpty(variableName))
            return false;
        String regex="^[_a-zA-Z]\\w{1,64}$";
        return Pattern.matches(regex,variableName);
    }
    /**
     * null "" " " all act as empty string
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(null==str||str.length()==0)
            return true;
        String regex = "^\\s+$";
        return Pattern.matches(regex,str);
    }

    /**
     * return whethe a string is numric number
     * 是否是整数
     * @param value
     * @return
     */
    public static boolean isNumric(String value){
        if(isEmpty(value))
            return false;
        String regex = "^[-]?\\d{0,19}$";
        return Pattern.matches(regex,value);
    }

    /**
     * 是否是int类型
     * @param value
     * @return
     */
    public static boolean isInt(String value){
        if(!isNumric(value))
            return false;
        Long l = Long.parseLong(value) - 1;
        if(l<Integer.MAX_VALUE&&l>Integer.MIN_VALUE)
            return true;
        return false;
    }

    /**
     * 是否是Long型
     * @param value
     * @return
     */
    public static boolean isLong(Object value){
        if(value==null)
            return false;
        if(value instanceof Long)
            return true;
        if(isNumric(value.toString()))
            return true;
        return false;
    }

    /**
     * 是否是浮点数
     * @param str
     * @return
     */
    public static boolean isFloat(String str){
        if(isEmpty(str))
            return false;
        if(isNumric(str))
            return true;
        String regex="[-]?\\d+(\\.(\\d+)?)?";
        return Pattern.matches(regex,str);
    }
    /**
     * return whethe object value is integer
     * @param value
     * @return
     */
    public static boolean isInteger(Object value){
        if(value==null)
            return false;
        if(value instanceof Integer){
            return true;
        }
        return isInt(value.toString());
    }
}
