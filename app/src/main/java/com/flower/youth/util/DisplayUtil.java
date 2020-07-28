package com.flower.youth.util;

import android.content.Context;

/**
 * Created by CJX on 2017/8/3.
 */

public class DisplayUtil {

    private DisplayUtil(){}

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
