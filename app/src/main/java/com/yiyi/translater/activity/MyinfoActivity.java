package com.yiyi.translater.activity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yiyi.translater.R;
import com.yiyi.translater.dao.Info_Dao;
import com.yiyi.translater.dao.SQLiteHelper;
import com.yiyi.translater.dialog.Update_Dialog;
import com.yiyi.translater.model.Info;

import org.w3c.dom.Text;

public class MyinfoActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView d;
    private TextView names;
    private TextView infonames;
    private String sex;
    private String age;
    private String email;
    private String phone;
    private TextView infosex;
    private TextView infoage;
    private TextView infoemail;
    private TextView infophone;
    private TextView upsex;
    private TextView upage;
    private TextView upemail;
    private TextView upphone;
    private Update_Dialog dialog;
    private String nameStr;
    private String contentStr;
    private static SQLiteDatabase db;
    private SQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        initViews();
        setViews();
        setListeners();
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
    }

    private void setListeners() {
        upsex.setOnClickListener(this);
        upage.setOnClickListener(this);
        upemail.setOnClickListener(this);
        upphone.setOnClickListener(this);
    }

    private void initViews() {
        toolbar = findViewById(R.id.info_toolbar);
        d = findViewById(R.id.info_id);
        names = findViewById(R.id.info_names);
        infosex = findViewById(R.id.info_sex);
        infoage = findViewById(R.id.info_age);
        infoemail = findViewById(R.id.info_email);
        infophone = findViewById(R.id.info_phone);
        upsex = findViewById(R.id.sex_update);
        upage = findViewById(R.id.age_update);
        upemail = findViewById(R.id.email_update);
        upphone = findViewById(R.id.phone_update);
        infonames = findViewById(R.id.info_names);
    }

    public void getDatas() {
        Info_Dao d = new Info_Dao(getApplication());
        sex = d.getSex();
        age = d.getAge();
        phone = d.getPhone();
        email = d.getEmail();
    }

    private void setViews() {
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences a = getSharedPreferences("ID", MODE_PRIVATE);
                String n = a.getString("id", "");
                Message m = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("iid", n);
                m.setData(b);
                m.what = 1;
                handler.sendMessage(m);
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bb = msg.getData();
            String nn = bb.getString("iid");
            d.setText("用户" + nn);
            names.setText(nn);
            getDatas();
            infosex.setText(sex);
            infoage.setText(age);
            infoemail.setText(email);
            infophone.setText(phone);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.age_update:
                dialog = new Update_Dialog(this);
                dialog.setTitle("请填写年龄");
                dialog.seted("age");
                dialog.setNoOnclickListener("取消", new Update_Dialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        dialog.dismiss();
                    }
                });
                dialog.setYesOnclickListener("确定", new Update_Dialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        nameStr = infonames.getText().toString();
                        contentStr = dialog.getContent();
                        if (!contentStr.equals("")) {
                            ContentValues cv = new ContentValues();
                            cv.put("age", contentStr);
                            String a = "userid=?";
                            db.update("tb_info", cv, a, new String[]{nameStr});
                            dialog.dismiss();
                            infoage.setText(contentStr);
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.sex_update:
                dialog = new Update_Dialog(this);
                dialog.setTitle("请填写性别");
                dialog.seted("sex");
                dialog.setYesOnclickListener("确定", new Update_Dialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        nameStr = infonames.getText().toString();
                        contentStr = dialog.getContent();
                        if (!contentStr.equals("")) {
                            ContentValues cv = new ContentValues();
                            cv.put("sex", contentStr);
                            String a = "userid=?";
                            db.update("tb_info", cv, a, new String[]{nameStr});
                            dialog.dismiss();
                            infosex.setText(contentStr);
                        }
                    }
                });
                dialog.setNoOnclickListener("取消", new Update_Dialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.email_update:
                dialog = new Update_Dialog(this);
                dialog.setTitle("请填写邮箱");
                dialog.seted("email");
                dialog.setNoOnclickListener("取消", new Update_Dialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        dialog.dismiss();
                    }
                });
                dialog.setYesOnclickListener("确定", new Update_Dialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        nameStr = infonames.getText().toString();
                        contentStr = dialog.getContent();
                        if (!contentStr.equals("")) {
                            ContentValues cv = new ContentValues();
                            cv.put("email", contentStr);
                            String a = "userid=?";
                            db.update("tb_info", cv, a, new String[]{nameStr});
                            dialog.dismiss();
                            infoemail.setText(contentStr);
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.phone_update:
                dialog = new Update_Dialog(this);
                dialog.setTitle("请填写电话号码");
                dialog.seted("phone");
                dialog.setNoOnclickListener("取消", new Update_Dialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        dialog.dismiss();
                    }
                });
                dialog.setYesOnclickListener("确定", new Update_Dialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        nameStr = infonames.getText().toString();
                        contentStr = dialog.getContent();
                        if (!contentStr.equals("")) {
                            ContentValues cv = new ContentValues();
                            cv.put("phone", contentStr);
                            String a = "userid=?";
                            db.update("tb_info", cv, a, new String[]{nameStr});
                            dialog.dismiss();
                            infophone.setText(contentStr);
                        }
                    }
                });
                dialog.show();
                break;
        }
    }
}