package com.flower.youth.service.impls;

import android.util.Log;

import com.flower.youth.dao.PostInfoReturn;
import com.flower.youth.dao.RegisterLoginReturn;
import com.flower.youth.presenter.OnNotify;
import com.flower.youth.service.interfaces.OnPostInfo;
import com.flower.youth.util.Constant;
import com.flower.youth.util.GsonUtil;
import com.flower.youth.util.OkHttpUtil;
import com.flower.youth.util.SharedPrefrenceUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CJX on 2017/8/3.
 */

public class PostInfoImp implements OnPostInfo {

    private OnNotify onNotify;

    public PostInfoImp(OnNotify onNotify) {
        this.onNotify = onNotify;
    }

    @Override
    public void postAdv(String station, String company,
                        String salary, String address,
                        String time, String desc) {

        OkHttpClient client = OkHttpUtil.getClient();
        RequestBody body = new FormBody.Builder()
                .add("type", String.valueOf(0))
                .add("userid", String.valueOf(SharedPrefrenceUtil.getUserID()))
                .add("post", station)
                .add("company", company)
                .add("money", salary)
                .add("workingplace", address)
                .add("workhour", time)
                .add("jobcontent", desc)
                .build();
        Request request = new Request.Builder()
                .url(Constant.HOST + "/school/addinfo")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNotify.notifyView(Constant.POST_ADV_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String str = response.body().string();
                    Log.d("**LoginImp", str);

                    PostInfoReturn ret = GsonUtil.json2Obj(str, PostInfoReturn.class);
                    if (ret.getCode() == 1){
                        onNotify.notifyView(Constant.POST_ADV_SUCCESS, null);
                    }else{
                        onNotify.notifyView(Constant.POST_ADV_ERROR, "发帖失败");
                    }

                }else {
                    onNotify.notifyView(Constant.POST_ADV_ERROR, String.valueOf(response.code()) + "错误");
                }
            }
        });
    }
}
