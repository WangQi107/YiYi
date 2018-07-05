package com.yiyi.translater.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyi.translater.R;
import com.yiyi.translater.dao.SQLiteHelper;
import com.yiyi.translater.fragment.Fragment_Dict;
import com.yiyi.translater.fragment.Fragment_My;
import com.yiyi.translater.fragment.Fragment_Translate;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton rbtranslate;
    private RadioButton rbdict;
    private RadioButton rbmy;
    private TextView maintitle;
    private long exitTime = 0;
    private SQLiteHelper helper;
    private static SQLiteDatabase db;
    int numcode;
    private static final String TAG = "Log";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setViews();
        setListeners();
        setDataBase();
        setid();
    }

    private void initViews() {
        radioGroup = findViewById(R.id.radiogroup);
        rbtranslate = findViewById(R.id.rb_translate);
        rbdict = findViewById(R.id.rb_dict);
        rbmy = findViewById(R.id.rb_my);
        maintitle = findViewById(R.id.main_title);
    }

    private void setViews() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fs = fm.beginTransaction();
        Fragment_Translate df01 = new Fragment_Translate();
        fs.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fs.replace(R.id.framgment_one, df01);
        fs.commit();
    }

    private void setListeners() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_translate:
                        maintitle.setText("Translate");
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction fs = fm.beginTransaction();
                        Fragment_Translate df01 = new Fragment_Translate();
                        fs.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fs.replace(R.id.framgment_one, df01);
                        fs.commit();
                        break;
                    case R.id.rb_dict:
                        maintitle.setText("Dictionary");
                        FragmentManager ffm = getSupportFragmentManager();
                        FragmentTransaction ffs = ffm.beginTransaction();
                        Fragment_Dict df02 = new Fragment_Dict();
                        ffs.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ffs.replace(R.id.framgment_one, df02);
                        ffs.commit();
                        break;
                    case R.id.rb_my:
                        maintitle.setText("Ucenter");
                        FragmentManager fmm = getSupportFragmentManager();
                        FragmentTransaction fss = fmm.beginTransaction();
                        Fragment_My df03 = new Fragment_My();
                        fss.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fss.replace(R.id.framgment_one, df03);
                        fss.commit();
                        break;
                }
            }
        });
    }

    private void setDataBase() {
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setid() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences a = getSharedPreferences("Start", MODE_PRIVATE);
                Boolean isFirst=a.getBoolean("isFirst",true);
                if (isFirst==true){
                    numcode = (int) ((Math.random() * 9 + 1) * 100000);
                    SharedPreferences aa = getSharedPreferences("ID", Context.MODE_PRIVATE);
                    aa.edit().putString("id",String.valueOf(numcode).toString()).commit();
                    a.edit().putBoolean("isFirst",false).commit();
                }
            }
        }).start();
    }
}
