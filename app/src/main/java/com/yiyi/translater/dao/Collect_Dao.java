package com.yiyi.translater.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yiyi.translater.model.Collect;

import java.util.ArrayList;
import java.util.List;

public class Collect_Dao {
    private SQLiteHelper helper;
    private SQLiteDatabase db;

    public Collect_Dao(Context ctx) {
        helper = new SQLiteHelper(ctx);
        db = helper.getReadableDatabase();
    }

    public long insertCollect(Collect collect) {
        ContentValues cv = new ContentValues();
        cv.put("userid",collect.getUserid());
        cv.put("collecttime",collect.getTime());
        cv.put("collectcontent", collect.getCotent());
        cv.put("collectyuan",collect.getYuan());
        return db.insert("tb_collect", null, cv);
    }

    public List<Collect> getCollect() {
        List<Collect> collects = new ArrayList<>();
        String sql = "select * from tb_collect";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            String userid=c.getString(c.getColumnIndex("userid"));
            String time=c.getString(c.getColumnIndex("collecttime"));
            String content = c.getString(c.getColumnIndex("collectcontent"));
            String yuan =c.getString(c.getColumnIndex("collectyuan"));
            Collect m = new Collect(userid,time,content,yuan);
            collects.add(0,m);
        }
        return collects;
    }
}
