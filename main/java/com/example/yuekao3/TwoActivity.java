package com.example.yuekao3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.yuekao3.bean.MyData;
import com.example.yuekao3.dapter.MyAdapter;
import com.example.yuekao3.mvp.pp.PerenterImpl;
import com.example.yuekao3.mvp.vieww.IView;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;

public class TwoActivity extends AppCompatActivity implements IView ,View.OnClickListener {
    private final int r = 1001;
    private ImageView saomiao;
    private ImageView huan;
    private SearchView searchview;
    private RecyclerView recy;
    private PerenterImpl perenter;
    private ArrayList<MyData.DataBean> list=new ArrayList<>();
    private Context context;
    private MyAdapter adapter;
    private boolean flag = true;
    private String url = "http://www.zhaoapi.cn/product/getCatagory?page=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
        perenter = new PerenterImpl(this);
        perenter.startRequest(url);
        adapter = new MyAdapter(list, this);
        recy.setAdapter(adapter);


    }

    private void initView() {
        saomiao = (ImageView) findViewById(R.id.saomiao);
        saomiao.setOnClickListener(this);
        huan = (ImageView) findViewById(R.id.huan);
        huan.setOnClickListener(this);

        searchview = (SearchView) findViewById(R.id.searchview);
        recy = (RecyclerView) findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == r && resultCode == RESULT_OK) {
            if (data != null) {
                String str = data.getStringExtra(Constant.CODED_CONTENT);
                Toast.makeText(this, "您扫描的是" + str, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void setCheng(MyData myData) {
        list.addAll(myData.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setBai(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saomiao:
                startActivityForResult(new Intent(TwoActivity.this, CaptureActivity.class), r);
                break;
            case R.id.huan:
                if (flag) {
                    list.clear();
                    perenter.startRequest(url);
                    recy.setLayoutManager(new LinearLayoutManager(TwoActivity.this));
                    adapter = new MyAdapter(list, TwoActivity.this);
                    recy.setAdapter(adapter);
                    flag = false;

                } else {
                    recy.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    list.clear();
                    perenter.startRequest(url);
                    adapter = new MyAdapter(list, TwoActivity.this);
                    recy.setAdapter(adapter);
                    flag = true;
                }
                break;
        }

        }
    }
