package com.example.mymemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "hr.db";
    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    //onCreate 최초 한번만 실행된 다시 사용할려면
    // 앱을 삭제하고 다시 설치해야함
    // 앱을 설치했을 때 실행할 구문
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("onCreate 호출됨");
        String sql = "create table if not exists emp("
                + " _id integer PRIMARY KEY autoincrement, "    //auto 자동으로 증가 되어서 들어감
                + " name text, "
                + " age integer, "
                + " mobile text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    //i가버전 받아오는 것
    }
}
