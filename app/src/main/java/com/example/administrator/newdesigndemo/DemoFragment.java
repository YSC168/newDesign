package com.example.administrator.newdesigndemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newdesigndemo.adapter.DemoRecyclerViewAdapter;
import com.example.administrator.newdesigndemo.adapter.DemoStaggerViewAdapter;
import com.example.administrator.newdesigndemo.bean.ImageBean;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class DemoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View mView;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DemoRecyclerViewAdapter mRecyclerViewAdapter;
    private ArrayList<ImageBean> list;
    private DemoStaggerViewAdapter demoStaggerViewAdapter;
    private static final int VERTICAL_LIST = 0;
    private static final int HORIZONTAL_LIST = 1;
    private static final int VERTICAL_GRID = 2;
    private static final int HORIZONTAL_GRID = 3;
    private static final int STAGGERED_GRID = 4;

    private static final int SPAN_COUNT = 2;
    private int flag = 0;
    String [] imgeurls;
    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_main, container, false);
        return mView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        flag = (int) getArguments().get("flag");
        imgeurls = getResources().getStringArray(R.array.image_url);
        initList();
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.id_swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.main_blue_light, R.color.main_blue_dark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        configRecyclerView();

    }

    private void configRecyclerView() {

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_recyclerview);

        switch (flag) {
            case VERTICAL_LIST:
                mLayoutManager =
                        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                break;
            case HORIZONTAL_LIST:
                mLayoutManager =
                        new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                break;
            case VERTICAL_GRID:
                mLayoutManager =
                        new GridLayoutManager(getActivity(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
                break;
            case HORIZONTAL_GRID:
                mLayoutManager =
                        new GridLayoutManager(getActivity(), 3, GridLayoutManager.HORIZONTAL, false);
                break;
            case STAGGERED_GRID:
                mLayoutManager =
                        new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
                break;
        }


        if (flag != STAGGERED_GRID) {
            mRecyclerViewAdapter = new DemoRecyclerViewAdapter(getActivity());
           // mRecyclerViewAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
            mRecyclerViewAdapter.setList(list);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            demoStaggerViewAdapter=new DemoStaggerViewAdapter(getActivity());
            //mRecyclerViewAdapter = new DemoRecyclerViewAdapter(getActivity());
            mRecyclerView.setAdapter(demoStaggerViewAdapter);
          //  mStaggeredAdapter = new MyStaggeredViewAdapter(getActivity());
          //  mStaggeredAdapter.setOnItemClickListener(this);
           // mRecyclerView.setAdapter(mStaggeredAdapter);
            demoStaggerViewAdapter.setList(list);
            mRecyclerView.setLayoutManager(mLayoutManager);
            demoStaggerViewAdapter.notifyDataSetChanged();
        }
    }


    private void initList(){
        list= new ArrayList<>();
        Random random = new Random();
        for (int i=0;i<30;i++){
            int temp = random.nextInt(imgeurls.length);
                ImageBean imageBean=new ImageBean();
                imageBean.setName("和你去旅行");
                imageBean.setImg(imgeurls[temp]);
                list.add(imageBean);
        }
    }


    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                mSwipeRefreshLayout.setRefreshing(false);

                if (flag != STAGGERED_GRID) {
                    initList();
                    mRecyclerViewAdapter.setList(list);
                    mRecyclerViewAdapter.notifyDataSetChanged();
                } else {
                    initList();
                    demoStaggerViewAdapter.setList(list);
                    demoStaggerViewAdapter.notifyDataSetChanged();
                }
            }
        }, 1000);
    }



}
