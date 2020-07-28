package com.flower.youth.presenter;

import com.flower.youth.service.impls.SplashCheckUserImp;
import com.flower.youth.service.interfaces.OnSplashCheckUser;
import com.flower.youth.util.Constant;
import com.flower.youth.view.interfaces.OnSplashTurn;

/**
 * Created by CJX on 2017/8/1.
 */

public class SplashCheckUserPre implements OnNotify{

    private OnSplashTurn onSplashTurn;
    private OnSplashCheckUser onSplashCheckUser;

    public SplashCheckUserPre(OnSplashTurn onSplashTurn) {
        this.onSplashTurn = onSplashTurn;
        onSplashCheckUser = new SplashCheckUserImp(this);
    }

    public void checkUser(){
        onSplashCheckUser.checkUser();
    }

    @Override
    public void notifyView(int flag, Object obj) {
        if (flag == Constant.TO_MAIN){
            onSplashTurn.turnToMain();
        }else if (flag == Constant.TO_LOGIN){
            onSplashTurn.turnToLogin();
        }
    }
}
