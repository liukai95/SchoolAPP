package com.flower.youth.presenter;

import com.flower.youth.dao.Account;
import com.flower.youth.dao.RegisterLoginReturn;
import com.flower.youth.service.impls.LoginImp;
import com.flower.youth.service.interfaces.OnLoginCheck;
import com.flower.youth.util.Constant;
import com.flower.youth.util.SharedPrefrenceUtil;
import com.flower.youth.view.interfaces.OnLogin;

/**
 * Created by CJX on 2017/8/2.
 */

public class LoginPre implements OnNotify {

    private OnLogin onLogin;
    private OnLoginCheck onLoginCheck;

    private String account;
    private String pass;

    public LoginPre(OnLogin onLogin) {
        this.onLogin = onLogin;
        this.onLoginCheck = new LoginImp(this);
    }

    @Override
    public void notifyView(int flag, Object obj) {
        if (flag == Constant.LOGIN_ERROR){
            onLogin.loginError((String)obj);

        }else if (flag == Constant.LOGIN_SUCCESS){
            onLogin.loginSuccess(account, pass);

            // 本地缓存
            SharedPrefrenceUtil.storeAccount(account, pass);
            SharedPrefrenceUtil.storeLoginIllegal(true);

            RegisterLoginReturn ret = (RegisterLoginReturn)obj;
            Account account = ret.getMessage().get(0);

            SharedPrefrenceUtil.storeID(account.getUserid());
            SharedPrefrenceUtil.storeNickName(account.getNickName());
            SharedPrefrenceUtil.storeJobTitle(account.getJobTitle());
            SharedPrefrenceUtil.storeHeadUrl(account.getHeadPortrait());

        }
    }

    public void login(String account, String pass){
        this.account = account;
        this.pass = pass;

        onLoginCheck.login(account, pass);
    }
}
