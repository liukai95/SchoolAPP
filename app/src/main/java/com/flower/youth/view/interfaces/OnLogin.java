package com.flower.youth.view.interfaces;

/**
 * Created by CJX on 2017/8/2.
 */

public interface OnLogin {

    void loginSuccess(String account, String pass);

    void loginError(String error);
}
