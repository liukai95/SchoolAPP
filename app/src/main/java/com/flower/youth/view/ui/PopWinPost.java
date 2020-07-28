package com.flower.youth.view.ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flower.youth.R;

/**
 * Created by CJX on 2017/8/3.
 */

public class PopWinPost extends PopupWindow {

    private View mainView;

    private TextView postAdvertise;

    private TextView postLostFound;

    private TextView postPostgraduate;

    public PopWinPost(Activity activity, int width, int height,
                      View.OnClickListener onClickListener) {
        super(activity);

        mainView = LayoutInflater.from(activity).inflate(R.layout.popwin_post, null);
        postAdvertise = (TextView) mainView.findViewById(R.id.post_advertise);
        postLostFound = (TextView) mainView.findViewById(R.id.post_lost_found);
        postPostgraduate = (TextView) mainView.findViewById(R.id.post_postgraduate);

        if (onClickListener != null){
            postAdvertise.setOnClickListener(onClickListener);
            postLostFound.setOnClickListener(onClickListener);
            postPostgraduate.setOnClickListener(onClickListener);
        }

        setContentView(mainView);
        setWidth(width);
        setHeight(height);
        setAnimationStyle(R.style.MainPopWinAnim);
        setBackgroundDrawable(new ColorDrawable(0));
    }
}
