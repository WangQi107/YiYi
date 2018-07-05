package com.yiyi.translater.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yiyi.translater.model.Info;

public class Info_Dao {
    private SQLiteHelper helper;
    private SQLiteDatabase db;
    private  String n;

    public Info_Dao(Context ctx) {
        helper = new SQLiteHelper(ctx);
        db = helper.getReadableDatabase();
        SharedPreferences a = ctx.getSharedPreferences("ID", Context.MODE_PRIVATE);
        n = a.getString("id", "");
    }

    public String getSex(){
        Cursor c=db.query("tb_info",new String[]{"sex"},"userid=?",new String[]{n},null,null,null,null);
        String usex=null;
        while (c.moveToNext()) {
            usex = c.getString(c.getColumnIndex("sex"));
        }
        return usex;
    }

    public String getAge(){
        Cursor c=db.query("tb_info",new String[]{"age"},"userid=?",new String[]{n},null,null,null,null);
        String uage=null;
        while (c.moveToNext()) {
            uage = c.getString(c.getColumnIndex("age"));
        }
        return uage;
    }

    public String getEmail(){
        Cursor c=db.query("tb_info",new String[]{"email"},"userid=?",new String[]{n},null,null,null,null);
        String uemail=null;
        while (c.moveToNext()) {
            uemail = c.getString(c.getColumnIndex("email"));
        }
        return uemail;
    }

    public String getPhone(){
        Cursor c=db.query("tb_info",new String[]{"phone"},"userid=?",new String[]{n},null,null,null,null);
        String uphone=null;
        while (c.moveToNext()) {
            uphone = c.getString(c.getColumnIndex("phone"));
        }
        return uphone;
    }

    public long insertInfo(Info info) {
        ContentValues cv = new ContentValues();
        cv.put("userid", info.getUserid());
        return db.insert("tb_info", null, cv);
    }
}
