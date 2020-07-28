package com.flower.youth.util;

import android.app.Activity;
import android.graphics.Color;

import com.jude.swipbackhelper.SwipeBackHelper;

/**
 * Created by CJX on 2017/3/17.
 */

public class SwipeBackUtil {

	private SwipeBackUtil(){}
	
    public static void config(Activity activity){
        SwipeBackHelper.getCurrentPage(activity)
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeEdge(50)//可滑动的范围。150表示为左边150px的屏幕
                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setClosePercent(0.8f)//触发关闭Activity百分比
                .setSwipeRelateEnable(true)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(100)//activity联动时的偏移量。默认500px。
                .setDisallowInterceptTouchEvent(false);//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
    }
}
