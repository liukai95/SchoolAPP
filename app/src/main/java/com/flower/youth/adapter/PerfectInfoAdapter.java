package com.flower.youth.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flower.youth.R;
import com.flower.youth.dao.PerfectInfoItem;
import com.flower.youth.view.interfaces.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CJX on 2017/8/2.
 */

public class PerfectInfoAdapter extends RecyclerView.Adapter<PerfectInfoAdapter.MViewHolder>{

    private List<PerfectInfoItem> itemList;
    private Context context;

    private OnItemClickListener onItemClickListener = null;

    public PerfectInfoAdapter(List<PerfectInfoItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.perfect_info_item, parent, false);
        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        PerfectInfoItem item = itemList.get(position);
        holder.tabImg.setImageResource(item.getTabImgSrc());
        holder.function.setText(item.getFunction());
        holder.turnImg.setImageResource(item.getTurnImgSrc());
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null){
                onItemClickListener.onClick(holder.itemView, position, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.perfect_info_item_tabImg)
        ImageView tabImg;

        @BindView(R.id.perfect_info_item_function)
        TextView function;

        @BindView(R.id.perfect_info_item_turn)
        ImageView turnImg;

        View itemView;

        public MViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            ButterKnife.bind(this, itemView);
        }
    }
}
