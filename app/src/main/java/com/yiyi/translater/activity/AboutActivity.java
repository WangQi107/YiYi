package com.yiyi.translater.activity;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyi.translater.R;
import com.yiyi.translater.dialog.Update_Dialog;

public class AboutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvsuggest;
    private Update_Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initViews();
        setViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.about_toolbar);
        tvsuggest=findViewById(R.id.tv_suggest);
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
        tvsuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Update_Dialog(AboutActivity.this);
                dialog.setTitle("留个言吧");
                dialog.seted("1");
                dialog.setYesOnclickListener("确定", new Update_Dialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"感谢支持",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNoOnclickListener("取消", new Update_Dialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}