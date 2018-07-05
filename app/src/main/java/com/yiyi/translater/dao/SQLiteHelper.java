package com.yiyi.translater.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Yi.db";
    private static final int DB_VERSION = 1;
    private static final String TB_COLLECT="create table tb_collect("
            +"_id integer primary key autoincrement,"
            +"userid varchar(0),"
            +"collecttime vachar(0),"
            +"collectyuan varchar(0),"
            +"collectcontent vachar(0))";
    private static final String TB_INFO="create table tb_info("
            +"_id integer primary key autoincrement,"
            +"userid varchar(0),"
            +"sex varchar(0),"
            +"age varchar(0),"
            +"email varchar(0),"
            +"phone varchar(0))";
    private static final String TB_SUGGEST="create table tb_suggest("
            +"_id integer primary key autoincrement,"
            +"time varchar(0),"
            +"suggest varchar(0))";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TB_COLLECT);
        db.execSQL(TB_INFO);
        db.execSQL(TB_SUGGEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
