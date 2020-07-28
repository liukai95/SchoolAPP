package com.flower.youth.service.impls;

import android.util.Log;

import com.flower.youth.dao.RegisterLoginReturn;
import com.flower.youth.presenter.OnNotify;
import com.flower.youth.service.interfaces.OnRegisterAccount;
import com.flower.youth.util.Constant;
import com.flower.youth.util.DESUtil;
import com.flower.youth.util.GsonUtil;
import com.flower.youth.util.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CJX on 2017/8/2.
 */

public class RegisterImp implements OnRegisterAccount {

    private OnNotify onNotify;

    public RegisterImp(OnNotify onNotify) {
        this.onNotify = onNotify;
    }

    @Override
    public void register(String account, String pass) {
        String des_pass = null;
        try {
            des_pass = DESUtil.encrypt(pass);
        } catch (Exception e) {
            onNotify.notifyView(Constant.REGIST_ERROR, e.getMessage());
            e.printStackTrace();
            return;
        }

        Log.d("**RegisterImp", account);
        Log.d("**RegisterImp", des_pass);

        OkHttpClient client = OkHttpUtil.getClient();
        RequestBody body = new FormBody.Builder()
                .add("tel",account)
                .add("password", des_pass)
                .add("nickname","昵称未填写")
                .add("jobtitle","职称未填写")
                .add("headportrait","/schoolpic/headportrait/1501744535894400美少女.png")
                .build();
        Request request = new Request.Builder()
                .url(Constant.HOST + "/school/regist")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNotify.notifyView(Constant.REGIST_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String str = response.body().string();
                    Log.d("**RegisterImp", str);

                    RegisterLoginReturn ret = GsonUtil.json2Obj(str, RegisterLoginReturn.class);
                    Log.d("**RegisterImp", String.valueOf(ret.getCode()));
                    Log.d("**RegisterImp", String.valueOf(ret.getMessageNum()));

                    if (ret.getCode() == 1){
                        onNotify.notifyView(Constant.REGIST_SUCCESS, null);
                    }else{
                        onNotify.notifyView(Constant.REGIST_ERROR, "注册失败");
                    }

                }else{
                    onNotify.notifyView(Constant.REGIST_ERROR,
                            String.valueOf(response.code()) + "错误");
                }
            }
        });
    }
}
