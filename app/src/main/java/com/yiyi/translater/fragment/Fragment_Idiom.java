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
public class Fragment_Idiom extends Fragment implements View.OnClickListener{
    private View v;
    private TextView tvIniom,tvIniomExpl;
    private EditText etIniomSearch;
    private ImageView ivIniomSearch;

    public Fragment_Idiom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v= inflater.inflate(R.layout.fragment_idiom, container, false);
       initViews();
       setListeners();
       return v;
    }

    private void setListeners() {
        etIniomSearch.setOnClickListener(this);
        ivIniomSearch.setOnClickListener(this);
    }

    private void initViews() {
        tvIniom=v.findViewById(R.id.tv_idiom);
        tvIniomExpl=v.findViewById(R.id.tv_idiom_explain);
        etIniomSearch=v.findViewById(R.id.et_idiom_search);
        ivIniomSearch=v.findViewById(R.id.iv_idiom_search);
    }

    @Override
    public void onClick(View view) {

    }
}
