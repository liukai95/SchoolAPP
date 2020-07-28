package com.flower.youth.view.interfaces;

import android.view.View;

/**
 * Created by CJX on 2017/8/2.
 */

public interface OnItemClickListener {

    void onClick(View view, int position, Object obj);

    void OnLongClick(View view, int position, Object obj);
}
