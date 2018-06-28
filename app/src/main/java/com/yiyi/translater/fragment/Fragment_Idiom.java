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
public class Fragment_Idiom extends Fragment {
    private TextView tvIniom,tvIniomExplain;
    private EditText etIniomSearch;
    private ImageView ivIniomSearch;
    private ListView lvIniom;

    public Fragment_Idiom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idiom, container, false);
    }

}
