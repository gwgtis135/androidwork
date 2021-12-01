package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtResult;

    ListView lvv;
    Button btnInserttt, btnselectAll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInserttt = findViewById(R.id.btnInserttt);
        btnselectAll = findViewById(R.id.btnselectAll);

        //listview 설정
        lvv = findViewById(R.id.lvv);
        List<String> result = new ArrayList<String>();
        //어뎁터가 데이터랑 뷰를 가지고 있는다 데이터만 바뀌면 바뀐 데이터가 뷰에 보여야 한다.
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                result);
        lvv.setAdapter(adapter);
        lvv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getApplicationContext(), result.get(i), Toast.LENGTH_SHORT).show();


    });

            //데이터 조회
        btnInserttt.setOnClickListener(view1 -> {
                DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());  //db, table
                SQLiteDatabase db = dbhelper.getReadableDatabase(); //DB
                String sql = "select * from emp";
                Cursor cursor = db.rawQuery(sql, null);


                //name을 리스트에 추가
                while (cursor.moveToNext()) {
                    String name = cursor.getString(1);
                    result.add(name);
                }
                db.close();
                lvv.getAdapter().notifyAll();

            });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            System.out.println("111111111111");
            Toast.makeText(getApplicationContext(),"등록되었습니다.",Toast.LENGTH_LONG).show();
            //data.getIntExtra("code", 0);
        }
    }



}
