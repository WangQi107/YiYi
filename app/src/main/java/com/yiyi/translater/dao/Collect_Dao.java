package com.yiyi.translater.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yiyi.translater.model.Collect;

public class Collect_Dao {
    private SQLiteHelper helper;
    private SQLiteDatabase db;

    public Collect_Dao(Context ctx) {
        helper = new SQLiteHelper(ctx);
        db = helper.getReadableDatabase();
    }

    public long insertCollect(Collect collect) {
        ContentValues cv = new ContentValues();
        cv.put("collecttime",collect.getTime());
        cv.put("collectcontent", collect.getCotent());
        return db.insert("tb_collect", null, cv);
    }
}
