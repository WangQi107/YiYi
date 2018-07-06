package com.yiyi.translater.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.yiyi.translater.R;
import com.yiyi.translater.adapter.Collect_Adapter;
import com.yiyi.translater.dao.Collect_Dao;
import com.yiyi.translater.model.Collect;

import java.util.List;

public class CollectActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView lvcollect;
    private Collect_Dao dao;
    private List<Collect> collects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initViews();
        setViews();
        setAdapter();
    }

    private void initViews() {
        toolbar = findViewById(R.id.collect_toolbar);
        lvcollect=findViewById(R.id.lv_collect);
    }

    private void setViews() {
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dao=new Collect_Dao(this);
        collects=dao.getCollect();
    }

    private void setAdapter() {
        Collect_Adapter adapter=new Collect_Adapter(this,collects);
        lvcollect.setAdapter(adapter);
    }
}
