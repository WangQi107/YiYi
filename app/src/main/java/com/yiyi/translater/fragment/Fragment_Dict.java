package com.yiyi.translater.fragment;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyi.translater.R;
import com.yiyi.translater.activity.SearchDictActivity;
import com.yiyi.translater.activity.SearchIdiomActivity;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Dict extends Fragment implements View.OnClickListener{
    private View v;
    private ImageButton ibDict,ibIniom;
    private Button btnDict,btnIniom;
    private TextView tvLeftIntro,tvRightIntro;
    private boolean net = false;
    public Fragment_Dict() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_dict, container, false);
        initViews();
        checknet();
        setListeners();
        return v;
    }

    private void checknet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(getActivity(), getString(R.string.neterror), Toast.LENGTH_SHORT).show();
        } else {
            net=true;
        }
    }

    private void setListeners() {
        ibDict.setOnClickListener( this);
        ibIniom.setOnClickListener( this);
        btnDict.setOnClickListener( this);
        btnIniom.setOnClickListener( this);
    }

    private void initViews() {
        ibDict=v.findViewById(R.id.ib_dict);
        ibIniom=v.findViewById(R.id.ib_idiom);
        btnDict=v.findViewById(R.id.btn_dict);
        btnIniom=v.findViewById(R.id.btn_idiom);
        tvLeftIntro=v.findViewById(R.id.tv_left_introduce);
        tvRightIntro=v.findViewById(R.id.tv_right_introduce);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dict:
                Intent i1=new Intent(getActivity(), SearchDictActivity.class);
                startActivity(i1);
                break;
            case R.id.btn_idiom:
                Intent i2=new Intent(getContext(), SearchIdiomActivity.class);
                startActivity(i2);
                break;
        }

    }
}
