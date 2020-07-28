package com.flower.youth.util;

import com.google.gson.Gson;

/**
 * Created by CJX on 2017/8/2.
 */

public class GsonUtil {

    private static Gson gson = new Gson();

    private GsonUtil(){}

    public static <T> T json2Obj(String json, Class<T> mClass){
        return gson.fromJson(json, mClass);
    }

}
