package com.yiyi.translater.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yiyi.translater.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ZiDian extends Fragment implements View.OnClickListener{
    private View v;
    private TextView tvZd,tvZdExpl;
    private EditText etZdSearch;
    private ImageView ivZdSearch;


    public Fragment_ZiDian() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_zidian, container, false);
        initViews();
        setListeners();
        return v;
    }

    private void setListeners() {
        etZdSearch.setOnClickListener(this);
        ivZdSearch.setOnClickListener(this);
    }

    private void initViews() {
        tvZd=v.findViewById(R.id.tv_zd);
        tvZdExpl=v.findViewById(R.id.tv_zd_explain);
        etZdSearch=v.findViewById(R.id.et_zd_search);
        ivZdSearch=v.findViewById(R.id.iv_zd_search);
    }

    @Override
    public void onClick(View view) {

    }
}
