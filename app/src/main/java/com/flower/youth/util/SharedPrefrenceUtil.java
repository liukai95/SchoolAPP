package com.flower.youth.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.flower.youth.MyApplication;

/**
 * Created by CJX on 2017/8/1.
 */

public class SharedPrefrenceUtil {

    private static SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(
            MyApplication.getContext());

    private static SharedPreferences.Editor editor = sh.edit();

    private static final String ACCOUNT = "ACCOUNT";
    private static final String PASS = "PASS";
    private static final String IS_ILLEGAL = "IS_ILLEGAL";
    private static final String REMEMBER = "REMEMBER";
    private static final String USER_ID = "USER_ID";
    private static final String NICKNAME = "NICKNAME";
    private static final String JOB_TITLE = "JOB_TITLE";
    private static final String AUTO_LOGIN = "AUTO_LOGIN";
    private static final String HEAD_URL = "HEAD_URL";

    private SharedPrefrenceUtil(){}

    /**
     * 存储账户和密码
     * */
    public static void storeAccount(String account, String pass){
        editor.putString(ACCOUNT, account);
        editor.putString(PASS, pass);
        editor.commit();
    }

    /**
     * 获取账号
     * */
    public static String getAccount(){
        return sh.getString(ACCOUNT, "");
    }

    /**
     * 获取密码
     * */
    public static String getPass(){
        return sh.getString(PASS, "");
    }

    /**
     * 存储是否合法登录
     * */
    public static void storeLoginIllegal(boolean isIllegal){
        editor.putBoolean(IS_ILLEGAL, isIllegal);
        editor.commit();
    }

    /**
     * 存储是否记住密码
     * */
    public static void storeRember(boolean remember){
        editor.putBoolean(REMEMBER, remember);
        editor.commit();
    }

    /**
     * 获取登录合法信息
     * */
    public static boolean isLoginIllegal(){
        return sh.getBoolean(IS_ILLEGAL, false);
    }

    /**
     * 获取是否记住密码
     * */
    public static boolean isRemember(){
        return sh.getBoolean(REMEMBER, false);
    }

    /**
     * 存储用户ID
     * */
    public static void storeID(int id){
        editor.putInt(USER_ID, id);
        editor.commit();
    }

    /**
     * 获取用户ID
     * */
    public static int getUserID(){
        return sh.getInt(USER_ID, Integer.MIN_VALUE);
    }

    /**
     * 存储昵称
     * */
    public static void storeNickName(String nickname){
        editor.putString(NICKNAME, nickname);
        editor.commit();
    }

    /**
     * 获取昵称
     * */
    public static String getNickName(){
        return sh.getString(NICKNAME, "");
    }

    /**
     * 存储职称
     * */
    public static void storeJobTitle(String job){
        editor.putString(JOB_TITLE, job);
        editor.commit();
    }

    /**
     * 获取昵称
     * */
    public static String getJobTitle(){
        return sh.getString(JOB_TITLE, "");
    }

    /**
     * 存储是否自动登录
     * */
    public static void storeAutoLogin(boolean autoLogin){
        editor.putBoolean(AUTO_LOGIN, autoLogin);
        editor.commit();
    }

    /**
     * 获取自动登录
     * */
    public static boolean isAutoLogin(){
        return sh.getBoolean(AUTO_LOGIN, false);
    }

    /**
     * 存储头像URL
     * */
    public static void storeHeadUrl(String url){
        editor.putString(HEAD_URL, url);
        editor.commit();
    }

    /**
     * 获取头像URL
     * */
    public static String getHeadUrl(){
        return sh.getString(HEAD_URL, "");
    }

    public static void clearAll(){
        editor.clear();
        editor.commit();
    }
}
