package com.flower.youth.presenter;

import android.graphics.Bitmap;

import com.flower.youth.service.impls.RefreshMainImp;
import com.flower.youth.service.interfaces.OnRefreshMain;
import com.flower.youth.util.Constant;
import com.flower.youth.view.interfaces.OnMainRefresh;

/**
 * Created by CJX on 2017/8/3.
 */

public class MainRefreshPre implements OnNotify {

    private OnMainRefresh onMainRefresh;
    private OnRefreshMain onRefreshMain;

    public MainRefreshPre(OnMainRefresh onMainRefresh) {
        this.onMainRefresh = onMainRefresh;
        this.onRefreshMain = new RefreshMainImp(this);
    }

    public void refreshHeadImg(String url){
        StringBuilder builder = new StringBuilder();
        builder.append(Constant.HOST);

        if (!url.startsWith("/schoolpic")){
            builder.append("/schoolpic");
        }

        builder.append(url);
        onRefreshMain.refreshHeadImg(builder.toString());
    }

    @Override
    public void notifyView(int flag, Object obj) {
        if (flag == Constant.REFRESH_HEAD_SUCCESS){
            onMainRefresh.refreshHeadImg((Bitmap)obj);

        }else if (flag == Constant.REFRESH_HEAD_ERROR){
            onMainRefresh.refreshHeadImgFail((String)obj);
        }
    }
}
