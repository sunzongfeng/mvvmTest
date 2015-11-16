package com.example.ht2015.test.group.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;


import com.example.ht2015.test.R;
import com.example.ht2015.test.group.adapter.ClassManagerAdapter;
import com.example.ht2015.test.group.view.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClassManagerActivity extends AppCompatActivity implements ClassManagerAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "ClassManagerActivity";
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ClassManagerAdapter mAdapter;
    private List<String> mDataEntities;

    private static final String EXTRA_GROUP_ID = "group_id";
    private int mGroupId;

    private int mPosition;
    private String mName;

    public static void actionClassManagerActivity(Context context, int groupId) {
        Intent intent = new Intent(context, ClassManagerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_GROUP_ID, groupId);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mDataEntities = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i ++){
            mDataEntities.add(i + "班");
        }

        mGroupId = getIntent().getIntExtra(EXTRA_GROUP_ID, 0);
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, getResources()
                        .getDisplayMetrics()));

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .drawable(R.drawable.item_diliver)
                        .marginResId(R.dimen.spacing_normal, R.dimen.spacing_normal)
                        .build());
        mAdapter = new ClassManagerAdapter(this);
        mAdapter.setItemClickListener(this);
        mAdapter.setData(mDataEntities);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        mPosition = position;
    }

    @Override
    public void onLongClick(View view, int position) {
        mPosition = position;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(false);
            }
        },2000);


    }

}
