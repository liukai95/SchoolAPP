package com.flower.youth.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by CJX on 2017/4/23.
 */

public class CameraPermission {

    private CameraPermission() {
    }

    public static boolean isPermited(Context context){

        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false;

        } else {
            return true;
        }

    }

    public static void requestPer(Activity activity, int requestCode){
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.CAMERA},
                requestCode);
    }

    /**
     * 拍照权限被拒绝时显示弹框
     * */
    public static void showCameraNotPer(Context context, String func_name){
        if (func_name == null || func_name.equals("")){
            func_name = "该";
        }

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .content(func_name + "功能需要打开摄像头")
                .positiveText("我知道了")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
        dialog.show();
    }
}
