package com.flower.youth.util;

import okhttp3.OkHttpClient;

/**
 * Created by CJX on 2017/8/3.
 */

public class OkHttpUtil {

    private OkHttpUtil(){}

    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient getClient(){
        if (okHttpClient == null){
            synchronized (OkHttpUtil.class){
                if (okHttpClient == null){
                    okHttpClient = new OkHttpClient();
                }
            }
        }

        return okHttpClient;

    }
}
