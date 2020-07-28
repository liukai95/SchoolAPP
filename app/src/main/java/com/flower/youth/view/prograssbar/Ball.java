package com.flower.youth.view.prograssbar;

/**
 * Created by Administrator on 2017/8/3.
 */

import android.support.annotation.Keep;

/**
 * 小球
 */
public class Ball {
    //防止混淆
    @Keep
    private float radius;//半径
    private float centerX;//圆心
    private int color;//颜色

    //防止混淆
    @Keep
    public float getRadius() {
        return radius;
    }

    //防止混淆
    @Keep
    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
