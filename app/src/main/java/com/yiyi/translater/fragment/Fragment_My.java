package com.yiyi.translater.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yiyi.translater.R;
import com.yiyi.translater.activity.AboutActivity;
import com.yiyi.translater.activity.CollectActivity;
import com.yiyi.translater.activity.MyinfoActivity;
import com.yiyi.translater.dao.Info_Dao;
import com.yiyi.translater.dao.SQLiteHelper;
import com.yiyi.translater.model.Collect;
import com.yiyi.translater.model.Info;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_My extends Fragment{
    private RelativeLayout recollect;
    private RelativeLayout remyinfo;
    private RelativeLayout reabout;
    View v;

    public Fragment_My() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my, container, false);
        initViews();
        setListeners();
        return v;
    }

    private void setListeners() {
        recollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),CollectActivity.class);
                startActivity(i);
            }
        });
        remyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getActivity(), MyinfoActivity.class);
                SharedPreferences aa = getContext().getSharedPreferences("INFO", getContext().MODE_PRIVATE);
                Boolean b=aa.getBoolean("First",true);
                if (b==true){
                    SharedPreferences a = getContext().getSharedPreferences("ID", getContext().MODE_PRIVATE);
                    String n = a.getString("id", "");
                    Info info=new Info(n);
                    Info_Dao dao=new Info_Dao(getContext());
                    long m=dao.insertInfo(info);
                    startActivity(ii);
                    aa.edit().putBoolean("First",false).commit();
                }else{
                    startActivity(ii);
                }
            }
        });
        reabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii=new Intent(getContext(), AboutActivity.class);
                startActivity(iii);
            }
        });
    }

    private void initViews() {
        recollect = v.findViewById(R.id.re_collect);
        remyinfo=v.findViewById(R.id.re_myinfo);
        reabout=v.findViewById(R.id.re_about);
    }
}
