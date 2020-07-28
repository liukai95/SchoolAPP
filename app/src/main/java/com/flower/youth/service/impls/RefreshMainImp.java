package com.flower.youth.service.impls;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.flower.youth.MyApplication;
import com.flower.youth.presenter.OnNotify;
import com.flower.youth.service.interfaces.OnRefreshMain;
import com.flower.youth.util.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by CJX on 2017/8/3.
 */

public class RefreshMainImp implements OnRefreshMain {

    private OnNotify onNotify;

    public RefreshMainImp(OnNotify onNotify) {
        this.onNotify = onNotify;
    }

    @Override
    public void refreshHeadImg(String url) {
        Log.d("**refreshHeadImg", url);

        ImageLoader loader = ImageLoader.getInstance();
        loader.loadImage(url, MyApplication.getOptions(), new SimpleImageLoadingListener(){

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String str = failReason.getType().name();
                Log.d("**refreshHeadImg", str);
                onNotify.notifyView(Constant.REFRESH_HEAD_ERROR, str);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                onNotify.notifyView(Constant.REFRESH_HEAD_SUCCESS, loadedImage);
            }
        });
    }
}
