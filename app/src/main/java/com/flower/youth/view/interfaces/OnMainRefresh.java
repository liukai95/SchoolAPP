package com.flower.youth.view.interfaces;

import android.graphics.Bitmap;

/**
 * Created by CJX on 2017/8/3.
 */

public interface OnMainRefresh {

    void refreshHeadImg(Bitmap bitmap);

    void refreshHeadImgFail(String error);

}
