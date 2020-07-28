package com.flower.youth.service.impls;

import com.flower.youth.presenter.OnNotify;
import com.flower.youth.service.interfaces.OnSplashCheckUser;
import com.flower.youth.util.Constant;
import com.flower.youth.util.SharedPrefrenceUtil;

/**
 * Created by CJX on 2017/8/1.
 */

public class SplashCheckUserImp implements OnSplashCheckUser {

    private OnNotify onNotify;

    public SplashCheckUserImp(OnNotify onNotify) {
        this.onNotify = onNotify;
    }

    @Override
    public void checkUser() {
        if(SharedPrefrenceUtil.isAutoLogin()
                && SharedPrefrenceUtil.isLoginIllegal()
                && !SharedPrefrenceUtil.getAccount().equals("")){

            onNotify.notifyView(Constant.TO_MAIN, null);
        }else {
            onNotify.notifyView(Constant.TO_LOGIN, null);
        }
    }
}
