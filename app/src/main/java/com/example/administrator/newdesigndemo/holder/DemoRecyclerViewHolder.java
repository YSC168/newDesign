package com.example.administrator.newdesigndemo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newdesigndemo.R;


/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class DemoRecyclerViewHolder extends RecyclerView.ViewHolder{

    public TextView textView;
    public ImageView imageView;

    public DemoRecyclerViewHolder(View itemView){
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.textview);
        imageView=(ImageView)itemView.findViewById(R.id.imageview);
    }
}
