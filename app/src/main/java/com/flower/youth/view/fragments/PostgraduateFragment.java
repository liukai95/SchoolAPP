package com.flower.youth.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flower.youth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 考研信息
 */
public class PostgraduateFragment extends Fragment {

    private View cacheView = null;

    @BindView(R.id.postgra_recycle)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cacheView = inflater.inflate(R.layout.fragment_postgraduate, container, false);
        ButterKnife.bind(this, cacheView);
        return cacheView;
    }

}
