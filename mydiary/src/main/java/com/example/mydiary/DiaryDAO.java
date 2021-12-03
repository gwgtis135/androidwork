package com.example.mydiary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DiaryDAO {
    DBHelper dbhelper;
    String tableName = "memo";

    //목록조회
    public static ArrayList<DiaryVO> selectAll(DBHelper dbhelper){
        ArrayList<DiaryVO> list = new ArrayList<DiaryVO>();

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql = "select _id, title, content, time, img from diary order by _id desc";
        Cursor cursor = db.rawQuery(sql, null); //null이 들어가는 이유 ?
        while (cursor.moveToNext()){
            DiaryVO diaryVO = new DiaryVO();
            diaryVO.setId(cursor.getString(0));
            diaryVO.setText(cursor.getString(1));
            diaryVO.setContent(cursor.getString(2));
            diaryVO.setTime(cursor.getString(3));
            diaryVO.setImg((cursor.getString(4)));
            list.add(diaryVO);
        }
        dbhelper.close();
        return list;    
    }
    
    //등록
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void insert(DBHelper dbhelper, DiaryVO diaryVO){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", diaryVO.getText());
        contentValues.put("content", diaryVO.getContent());
        if (diaryVO.getImg() != null){
            contentValues.put("img", diaryVO.getImg());
        }
        
        //현재시간 설정
        LocalDate dt = LocalDate.now();
        String sdt = dt.format(DateTimeFormatter.ISO_DATE);
        contentValues.put("time", sdt);
        
        db.insert("diary", null, contentValues);
        dbhelper.close();

    }

    //삭제
    public  void delete(DBHelper dbhelper,DiaryVO diaryVO){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",diaryVO.getId());
        String id = diaryVO.getId();
        db.delete("diary", "_id=?", new String[]{id}) ;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public  void update(DBHelper dbhelper, DiaryVO diaryVO){
        SQLiteDatabase db = dbhelper.getWritableDatabase();//데이터베이스 연결
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", diaryVO.getText());
        contentValues.put("content", diaryVO.getContent());

        if(diaryVO.getImg() != null) {
            contentValues.put("img", diaryVO.getImg());
        }

        //현재시간 설정
        LocalDate dt = LocalDate.now();
        String sdt = dt.format(DateTimeFormatter.ISO_DATE);
        contentValues.put("time", sdt);

        String id = diaryVO.getId();
        db.update("diary", contentValues, "_id=?",new String[]{id});
        dbhelper.close();
    }


}
