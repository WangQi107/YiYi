package com.yiyi.translater.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyi.translater.R;

public class SearchIdiomActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvIdiom;
    private EditText etIdiomSea;
    private ImageView ivIdiomSea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_idiom);
        initViews();
        setListeners();
    }

    private void setListeners() {
        etIdiomSea.setOnClickListener(this);
        ivIdiomSea.setOnClickListener(this);
    }

    private void initViews() {
        tvIdiom=findViewById(R.id.tv_idiom);
        etIdiomSea=findViewById(R.id.et_idiom_search);
        ivIdiomSea=findViewById(R.id.iv_idiom_search);
    }

    @Override
    public void onClick(View view) {

    }
}
