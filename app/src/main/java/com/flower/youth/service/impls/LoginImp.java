package com.flower.youth.service.impls;

import android.util.Log;

import com.flower.youth.dao.RegisterLoginReturn;
import com.flower.youth.presenter.OnNotify;
import com.flower.youth.service.interfaces.OnLoginCheck;
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

public class LoginImp implements OnLoginCheck {

    private OnNotify onNotify;

    public LoginImp(OnNotify onNotify) {
        this.onNotify = onNotify;
    }

    @Override
    public void login(String account, String pass) {
        String des_pass = null;
        try {
            des_pass = DESUtil.encrypt(pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient client = OkHttpUtil.getClient();
        RequestBody body = new FormBody.Builder()
                .add("tel",account)
                .add("password", des_pass)
                .build();
        Request request = new Request.Builder()
                .url(Constant.HOST + "/school/login")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNotify.notifyView(Constant.LOGIN_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String str = response.body().string();
                    Log.d("**LoginImp", str);

                    RegisterLoginReturn ret = GsonUtil.json2Obj(str, RegisterLoginReturn.class);
                    if (ret.getCode() == 1){
                        onNotify.notifyView(Constant.LOGIN_SUCCESS, ret);
                    }else{
                        onNotify.notifyView(Constant.LOGIN_ERROR, "账号或密码错误");
                    }

                }else {
                    onNotify.notifyView(Constant.LOGIN_ERROR, String.valueOf(response.code()) + "错误");
                }
            }
        });
    }
}
