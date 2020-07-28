package com.flower.youth.service.impls;

import android.util.Log;

import com.flower.youth.dao.LoadFileReturn;
import com.flower.youth.dao.PerfectInfoReturn;
import com.flower.youth.presenter.OnNotify;
import com.flower.youth.service.interfaces.OnPerfectInfo;
import com.flower.youth.util.Constant;
import com.flower.youth.util.DESUtil;
import com.flower.youth.util.GsonUtil;
import com.flower.youth.util.OkHttpUtil;
import com.flower.youth.util.SharedPrefrenceUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CJX on 2017/8/3.
 */

public class PerfectInfoImp implements OnPerfectInfo {

    private OnNotify onNotify;

    private OkHttpClient okHttpClient = null;

    public PerfectInfoImp(OnNotify onNotify) {
        this.onNotify = onNotify;
        okHttpClient = OkHttpUtil.getClient();
    }

    @Override
    public void changeHead(String url) {
        Log.d("**changeHead", url);

        File file = new File(url);
        String fileName = file.getName();
        Log.d("**changeHead", fileName);

        StringBuilder sb = new StringBuilder();
        sb.append(Constant.HOST).append("/school/picupload")
                .append("?type=0").append("&filename=")
                .append(fileName);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("pic", fileName, RequestBody.create(null, file));

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(sb.toString())
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String s = e.getMessage();
                Log.d("**changeHead", s);
                onNotify.notifyView(Constant.LOAD_HEAD_ERROR, s);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String str = response.body().string();
                    Log.d("**changeHead", str);

                    LoadFileReturn ret = GsonUtil.json2Obj(str, LoadFileReturn.class);
                    if(ret.getCode() == 1){
                        String headUrl = ret.getMessage().get(0);
                        onNotify.notifyView(Constant.LOAD_HEAD_SUCCESS, headUrl);

                    }else {
                        onNotify.notifyView(Constant.LOAD_HEAD_ERROR, "文件上传失败");
                    }

                }else {
                    onNotify.notifyView(Constant.LOAD_HEAD_ERROR,
                            String.valueOf(response.code()) + "错误");
                }
            }
        });

    }

    @Override
    public void changeNickName(String nickname) {
        Log.d("**changeNickName", String.valueOf(SharedPrefrenceUtil.getUserID()));

        RequestBody body = new FormBody.Builder()
                .add("userid", String.valueOf(SharedPrefrenceUtil.getUserID()))
                .add("nickname", nickname)
                .build();
        Request request = new Request.Builder()
                .url(Constant.HOST + "/school/updateuserinfo")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNotify.notifyView(Constant.CHANGE_NICKNAME_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String str = response.body().string();
                    Log.d("**changeNickName", str);

                    PerfectInfoReturn ret = GsonUtil.json2Obj(str, PerfectInfoReturn.class);
                    Log.d("**changeNickName", String.valueOf(ret.getCode()));

                    if (ret.getCode() == 1){
                        onNotify.notifyView(Constant.CHANGE_NICKNAME_SUCCESS, null);
                    }else{
                        onNotify.notifyView(Constant.CHANGE_NICKNAME_ERROR, "修改昵称失败");
                    }

                }else{
                    onNotify.notifyView(Constant.CHANGE_NICKNAME_ERROR,
                            String.valueOf(response.code()) + "错误");
                }
            }
        });
    }

    @Override
    public void changJobTitle(String job) {
        Log.d("**changJobTitle", String.valueOf(SharedPrefrenceUtil.getUserID()));

        RequestBody body = new FormBody.Builder()
                .add("userid", String.valueOf(SharedPrefrenceUtil.getUserID()))
                .add("jobtitle", job)
                .build();
        Request request = new Request.Builder()
                .url(Constant.HOST + "/school/updateuserinfo")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNotify.notifyView(Constant.CHANGE_JOB_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String str = response.body().string();
                    Log.d("**changJobTitle", str);

                    PerfectInfoReturn ret = GsonUtil.json2Obj(str, PerfectInfoReturn.class);
                    Log.d("**changJobTitle", String.valueOf(ret.getCode()));

                    if (ret.getCode() == 1){
                        onNotify.notifyView(Constant.CHANGE_JOB_SUCCESS, null);
                    }else{
                        onNotify.notifyView(Constant.CHANGE_JOB_ERROR, "修改职称失败");
                    }

                }else{
                    onNotify.notifyView(Constant.CHANGE_JOB_ERROR,
                            String.valueOf(response.code()) + "错误");
                }
            }
        });
    }

    @Override
    public void changePass(String pass) {
        Log.d("**changePass", String.valueOf(SharedPrefrenceUtil.getUserID()));

        String des_pass = null;
        try {
            des_pass = DESUtil.encrypt(pass);
        } catch (Exception e) {
            e.printStackTrace();
            onNotify.notifyView(Constant.CHANGE_PASS_ERROR, e.getMessage());
            return;
        }

        RequestBody body = new FormBody.Builder()
                .add("userid", String.valueOf(SharedPrefrenceUtil.getUserID()))
                .add("password", des_pass)
                .build();
        Request request = new Request.Builder()
                .url(Constant.HOST + "/school/updateuserinfo")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNotify.notifyView(Constant.CHANGE_PASS_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String str = response.body().string();
                    Log.d("**changePass", str);

                    PerfectInfoReturn ret = GsonUtil.json2Obj(str, PerfectInfoReturn.class);
                    Log.d("**changePass", String.valueOf(ret.getCode()));

                    if (ret.getCode() == 1){
                        onNotify.notifyView(Constant.CHANGE_PASS_SUCCESS, null);
                    }else{
                        onNotify.notifyView(Constant.CHANGE_PASS_ERROR, "密码修改失败");
                    }

                }else{
                    onNotify.notifyView(Constant.CHANGE_PASS_ERROR,
                            String.valueOf(response.code()) + "错误");
                }
            }
        });
    }

    @Override
    public void updateUserHead(String url){
        Log.d("**updateUserHead", String.valueOf(SharedPrefrenceUtil.getUserID()));

        RequestBody body = new FormBody.Builder()
                .add("userid", String.valueOf(SharedPrefrenceUtil.getUserID()))
                .add("headportrait", url)
                .build();
        Request request = new Request.Builder()
                .url(Constant.HOST + "/school/updateuserinfo")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNotify.notifyView(Constant.UPDATE_HEAD_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String str = response.body().string();
                    Log.d("**changePass", str);

                    PerfectInfoReturn ret = GsonUtil.json2Obj(str, PerfectInfoReturn.class);
                    Log.d("**changePass", String.valueOf(ret.getCode()));

                    if (ret.getCode() == 1){
                        onNotify.notifyView(Constant.UPDATE_HEAD_SUCCESS, url);
                    }else{
                        onNotify.notifyView(Constant.UPDATE_HEAD_ERROR, "头像修改失败");
                    }

                }else{
                    onNotify.notifyView(Constant.UPDATE_HEAD_ERROR,
                            String.valueOf(response.code()) + "错误");
                }
            }
        });
    }
}
