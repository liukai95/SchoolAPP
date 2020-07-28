package com.flower.youth.dao;

/**
 * Created by CJX on 2017/8/2.
 */

public class PerfectInfoItem {

    private int tabImgSrc;

    private String function;

    private int turnImgSrc;

    public PerfectInfoItem() {
    }

    public PerfectInfoItem(int tabImgSrc, String function, int turnImgSrc) {
        this.tabImgSrc = tabImgSrc;
        this.function = function;
        this.turnImgSrc = turnImgSrc;
    }

    public int getTabImgSrc() {
        return tabImgSrc;
    }

    public void setTabImgSrc(int tabImgSrc) {
        this.tabImgSrc = tabImgSrc;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getTurnImgSrc() {
        return turnImgSrc;
    }

    public void setTurnImgSrc(int turnImgSrc) {
        this.turnImgSrc = turnImgSrc;
    }
}
