package com.yiyi.translater.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yiyi.translater.R;

public class AboutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initViews();
        setViews();
    }

    private void initViews() {
        toolbar=findViewById(R.id.about_toolbar);
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
    }
}
