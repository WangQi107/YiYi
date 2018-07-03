package com.yiyi.translater.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.yiyi.translater.R;

public class Choose_Dialog extends Dialog{
    private TextView t;
    public Choose_Dialog(Context context) {
        super(context, R.style.DialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_dialog);
        initViews();
    }

    private void initViews() {
        t=findViewById(R.id.ctitle);
    }
}
