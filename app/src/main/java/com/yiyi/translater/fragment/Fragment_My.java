package com.yiyi.translater.fragment;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yiyi.translater.R;
import com.yiyi.translater.activity.AboutActivity;
import com.yiyi.translater.activity.CollectActivity;
import com.yiyi.translater.activity.MyinfoActivity;
import com.yiyi.translater.dao.SQLiteHelper;
import com.yiyi.translater.model.Collect;

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
                startActivity(ii);
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
