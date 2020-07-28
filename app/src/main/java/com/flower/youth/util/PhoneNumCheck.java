package com.flower.youth.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CJX on 2017/2/22.
 */

public class PhoneNumCheck {

	private PhoneNumCheck(){}
	
    /**
     * 检查是否为手机号
     * */
    public static boolean isPhoneNum(String s){

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(s);
        if(m.matches()){
            return true;
        }

        return false;
    }
}
