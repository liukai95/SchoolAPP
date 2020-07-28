package com.flower.youth.presenter;

import com.flower.youth.service.impls.PostInfoImp;
import com.flower.youth.service.interfaces.OnPostInfo;
import com.flower.youth.util.Constant;
import com.flower.youth.view.interfaces.OnAdvPost;

/**
 * Created by CJX on 2017/8/3.
 */

public class PostInfoPre implements OnNotify{

    private OnAdvPost onAdvPost;
    private OnPostInfo onPostInfo;

    public PostInfoPre(OnAdvPost onAdvPost) {
        this.onAdvPost = onAdvPost;
        this.onPostInfo = new PostInfoImp(this);
    }

    public void postAdv(String station, String company,
                        String salary, String address,
                        String time, String desc){
        onPostInfo.postAdv(station, company, salary, address, time, desc);
    }

    @Override
    public void notifyView(int flag, Object obj) {
        if (flag == Constant.POST_ADV_ERROR){
            onAdvPost.postFail((String)obj);

        }else if (flag == Constant.POST_ADV_SUCCESS){
            onAdvPost.postSuccess();
        }
    }
}
