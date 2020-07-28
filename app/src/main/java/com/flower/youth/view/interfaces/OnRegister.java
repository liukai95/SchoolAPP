package com.flower.youth.view.interfaces;

/**
 * Created by CJX on 2017/8/2.
 */

public interface OnRegister {

    void registerSuccess(String account, String pass);

    void registerError(String error);
}
