package com.flower.youth.presenter;

import com.flower.youth.service.impls.PerfectInfoImp;
import com.flower.youth.service.interfaces.OnPerfectInfo;
import com.flower.youth.util.Constant;
import com.flower.youth.util.SharedPrefrenceUtil;
import com.flower.youth.view.interfaces.OnInfoPerfect;

/**
 * Created by CJX on 2017/8/3.
 */

public class PerfectInfoPre implements OnNotify {

    private OnInfoPerfect onInfoPerfect = null;
    private OnPerfectInfo onPerfectInfo = null;

    private String nickname;
    private String job;
    private String pass;

    public PerfectInfoPre(OnInfoPerfect onInfoPerfect) {
        this.onInfoPerfect = onInfoPerfect;
        this.onPerfectInfo = new PerfectInfoImp(this);
    }

    public void changeHead(String url) {
        onPerfectInfo.changeHead(url);
    }

    public void changeNickName(String nickname) {
        this.nickname = nickname;
        onPerfectInfo.changeNickName(nickname);
    }

    public void changJobTitle(String job) {
        this.job = job;
        onPerfectInfo.changJobTitle(job);
    }

    public void changePass(String pass) {
        this.pass = pass;
        onPerfectInfo.changePass(pass);
    }

    @Override
    public void notifyView(int flag, Object obj) {
        if (flag == Constant.CHANGE_NICKNAME_SUCCESS){
            SharedPrefrenceUtil.storeNickName(nickname);
            onInfoPerfect.changeNickNameSuccess();

        }else if (flag == Constant.CHANGE_NICKNAME_ERROR){
            onInfoPerfect.changeNickNameFail((String)obj);

        }else if (flag == Constant.CHANGE_JOB_SUCCESS){
            SharedPrefrenceUtil.storeJobTitle(job);
            onInfoPerfect.changeJobTitleSuccess();

        }else if (flag == Constant.CHANGE_JOB_ERROR){
            onInfoPerfect.changeJobTitleFail((String)obj);

        }else if (flag == Constant.CHANGE_PASS_ERROR){
            onInfoPerfect.changePassFail((String)obj);

        }else if (flag == Constant.CHANGE_PASS_SUCCESS){
            SharedPrefrenceUtil.storeAccount(SharedPrefrenceUtil.getAccount(), pass);
            onInfoPerfect.changePassSuccess();

        }else if (flag == Constant.LOAD_HEAD_ERROR){
            onInfoPerfect.changeHeadFail((String)obj);

        }else if (flag == Constant.LOAD_HEAD_SUCCESS){
            // 更改用户信息
            onPerfectInfo.updateUserHead((String)obj);

        }else if (flag == Constant.UPDATE_HEAD_ERROR){
            onInfoPerfect.changeHeadFail((String)obj);

        }else if (flag == Constant.UPDATE_HEAD_SUCCESS){
            SharedPrefrenceUtil.storeHeadUrl((String)obj);
            onInfoPerfect.changeHeadSuccess();

        }
    }
}
