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
public class DemoRecyclerViewAdapter extends RecyclerView.Adapter<DemoRecyclerViewHolder> {
    private Context context;
    private ArrayList<ImageBean> list;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public DemoRecyclerViewAdapter(Context mContext) {
        this.context = mContext;

    }
    @Override
    public DemoRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(context).inflate(R.layout.item_demo_adapter,parent,false);
        DemoRecyclerViewHolder recyclerViewHolder=new DemoRecyclerViewHolder(mView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final DemoRecyclerViewHolder holder, final int position) {
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
        holder.textView.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getImg()).into(holder.imageView);

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<ImageBean> getList() {
        return list;
    }

    public void setList(ArrayList<ImageBean> list) {
        this.list = list;
    }
}
//http://pic1.nipic.com/2008-10-30/200810309416546_2.jpg
//http://pic24.nipic.com/20121003/10754047_140022530392_2.jpg
//http://pic3.nipic.com/20090605/2166702_095614055_2.jpg
//http://pic1.nipic.com/2008-11-13/2008111384358912_2.jpg