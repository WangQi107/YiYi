package com.yiyi.translater.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yiyi.translater.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Dict extends Fragment {
    private ImageButton ibDict,ibIniom;
    private Button btnDict,btnIniom;
    private TextView tvLeftIntro,tvRightIntro;
    public Fragment_Dict() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dict, container, false);
    }

}
