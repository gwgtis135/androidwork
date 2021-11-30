package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    EditText txtName,txtId,txtPhone,txtAge,txtSearchId;
    TextView extradata;
    Button btnIns,btnDel,btnOneSel,moveSelect, btnUpdate,btnExtraDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());  //db, table
        //위젯 초기화
        txtName = findViewById(R.id.txtName);
        txtId = findViewById(R.id.txtId);
        txtPhone = findViewById(R.id.txtPhone);
        txtAge = findViewById(R.id.txtAge);
        txtSearchId = findViewById(R.id.txtSearchId);
        extradata = findViewById(R.id.extradata);

        btnIns = findViewById(R.id.btnIns);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDel = findViewById(R.id.btnDel);
        btnOneSel = findViewById(R.id.btnOneSel);
        moveSelect = findViewById(R.id.moveSelect);
        btnExtraDate = findViewById(R.id.btnExtraDate);
        System.out.println(txtName);

//        //데이터 수신
//        btnExtraDate.setOnClickListener(view -> {
//            Intent intent1 = getIntent();
//
//
//            String name1 = intent1.getExtras().getString("name1");
//            int age1 = intent1.getExtras().getInt("age1"); /*int형*/
//            DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());  //db, table
//
//
//            extradata.setText(name1);
//
//        });




        //전체 조회버튼 누르면 등록페이지로 이동
        moveSelect.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        });

        //등록
        btnIns.setOnClickListener(view -> {
            SQLiteDatabase db = dbhelper.getWritableDatabase(); //DB
            String name = txtName.getText().toString();
            String phone = txtPhone.getText().toString();
            String age = txtAge.getText().toString();
            String id = txtId.getText().toString();

            String sqlInsert =  "INSERT INTO emp " +
                    "(name, age, mobile) VALUES (?,?,?)" ;
            db.execSQL(sqlInsert, new Object[]{name,age,phone});
            db.close();
            Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
            //mainintent.putExtra("msg","1");
            setResult(1, mainintent);

            finish();

        });

//        //등록
//        btnIns.setOnClickListener(v->{
//            SQLiteDatabase db = dbhelper.getWritableDatabase(); //DB
//            String name = txtName.getText().toString();
//            String phone = txtPhone.getText().toString();
//            String age = txtAge.getText().toString();
//            String id = txtId.getText().toString();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("title", memo.getTitle());
//            contentValues.put("content", memo.getContent());
//            String id = Integer.toString(memo.get_id());
//            db.update(tableName, contentValues, "_id=?", new String[]{id}) ;
//        });

        //단건 조회
        btnOneSel.setOnClickListener(view -> {
            String no = txtSearchId.getText().toString();

            SQLiteDatabase db=dbhelper.getReadableDatabase();
            String sql = "select _id, name, age, mobile from emp where _id ="+no ;
            Cursor cursor=db.rawQuery(sql, null);

            while(cursor.moveToNext()) {
                txtName.setText(cursor.getString(0));
                txtId.setText(cursor.getString(1));
                txtPhone.setText(cursor.getString(2));
                txtAge.setText(cursor.getString(3));
            }
        });
        //삭제
        btnDel.setOnClickListener(view -> {


            String id = txtSearchId.getText().toString();
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            db.delete("emp", "_id=?", new String[]{id}) ;


        });



    }
}