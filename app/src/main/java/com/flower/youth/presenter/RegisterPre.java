package com.flower.youth.presenter;

import com.flower.youth.service.impls.RegisterImp;
import com.flower.youth.service.interfaces.OnRegisterAccount;
import com.flower.youth.util.Constant;
import com.flower.youth.util.SharedPrefrenceUtil;
import com.flower.youth.view.interfaces.OnRegister;

/**
 * Created by CJX on 2017/8/2.
 */

public class RegisterPre implements OnNotify {

    private OnRegister onRegister;
    private OnRegisterAccount onRegisterAccount;

    private String account;
    private String pass;

    public RegisterPre(OnRegister onRegister) {
        this.onRegister = onRegister;
        this.onRegisterAccount = new RegisterImp(this);
    }

    public void register(String account, String pass){
        this.account = account;
        this.pass = pass;
        onRegisterAccount.register(account, pass);
    }

    @Override
    public void notifyView(int flag, Object obj) {
        if (flag == Constant.REGIST_ERROR){
            onRegister.registerError((String)obj);

        }else if (flag == Constant.REGIST_SUCCESS){
            onRegister.registerSuccess(account, pass);

            // 本地缓存
            SharedPrefrenceUtil.storeAccount(account, pass);
        }
    }
}
