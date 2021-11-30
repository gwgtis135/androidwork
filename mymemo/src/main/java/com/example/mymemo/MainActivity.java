package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText txtResult;
    Button btnSel, moveInsertPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);


        btnSel = findViewById(R.id.btnSel);
        moveInsertPage = findViewById(R.id.moveInsertPage);

        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());  //db, table




        moveInsertPage.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            startActivityForResult(intent,1);
        });

        btnSel.setOnClickListener(view -> {
            //목록조회

            ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            String sql = "select _id, name, age, mobile from emp order by _id desc ";
            Cursor cursor=db.rawQuery(sql, null);
            while (cursor.moveToNext()){
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("_id", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("age", cursor.getString(2));
                map.put("mobile", cursor.getString(3));
                list.add(map);
            }

            String sum = "";
            for (int i =0; i <list.size();i++){
                sum += "id :"+list.get(i).get("_id")+"\n"+"name :"+list.get(i).get("name")+"\n"+"age :"+list.get(i).get("age")+"\n"+
                        "mobile :"+list.get(i).get("mobile")+"\n";


            }

            txtResult.setText(sum);

        });
//        btnreset.setOnClickListener(view -> {
//            txtResult.getText("");
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            System.out.println("111111111111");
            Toast.makeText(getApplicationContext(),"등록되었습니다.",Toast.LENGTH_LONG);
            //data.getIntExtra("code", 0);
        }
    }



}
