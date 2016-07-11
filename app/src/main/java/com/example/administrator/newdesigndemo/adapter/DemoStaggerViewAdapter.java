package com.example.administrator.newdesigndemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.newdesigndemo.R;
import com.example.administrator.newdesigndemo.bean.ImageBean;
import com.example.administrator.newdesigndemo.holder.DemoRecyclerViewHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class DemoStaggerViewAdapter extends  RecyclerView.Adapter<DemoRecyclerViewHolder> {


    public LayoutInflater mLayoutInflater;

    public Context mContext;
    public ArrayList<ImageBean> list;
    public ArrayList<Integer> mHeights;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }



    public void setList(ArrayList<ImageBean> list) {
        this.list = list;
        mHeights = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            mHeights.add((int) (Math.random() * 300) + 200);
        }
    }

    public DemoStaggerViewAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DemoRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.item_demo_adapter, parent, false);
        DemoRecyclerViewHolder mViewHolder = new DemoRecyclerViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final DemoRecyclerViewHolder holder,final int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }

        ViewGroup.LayoutParams mLayoutParams = holder.imageView.getLayoutParams();
        mLayoutParams.height = mHeights.get(position);
        holder.imageView.setLayoutParams(mLayoutParams);
        Glide.with(mContext).load(list.get(position).getImg()).into(holder.imageView);
        holder.textView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
