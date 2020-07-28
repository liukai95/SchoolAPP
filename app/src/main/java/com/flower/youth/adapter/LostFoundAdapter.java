package com.flower.youth.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.flower.youth.dao.LostFoundItem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by CJX on 2017/8/2.
 */

public class LostFoundAdapter extends RecyclerView.Adapter<LostFoundAdapter.MViewHolder>{

    private List<LostFoundItem> lostFoundItemList;


    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MViewHolder extends RecyclerView.ViewHolder{

        public MViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
