package com.example.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WriteActive extends AppCompatActivity {

    Button btnSave, btnImage;
    EditText editTitle, editContent;
    DBHelper dbhelper;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        dbhelper = new DBHelper(getApplicationContext());

        DiaryDAO diarydao = new DiaryDAO();
        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);
        editContent = findViewById(R.id.editContent);
        editTitle = findViewById(R.id.editTitle);


        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String title = intent.getExtras().getString("text");
        String content = intent.getExtras().getString("content");
        System.out.println(title);
        System.out.println(content);

        editTitle.setText(title);
        editContent.setText(content);



        btnSave.setOnClickListener(view -> {
            //입력한 값 어뎁터에
            DiaryVO dirVO = new DiaryVO();
            dirVO.setText(editTitle.getText().toString());
            dirVO.setContent(editContent.getText().toString());
            if(intent.getExtras() !=null){
                diarydao.update(dbhelper,dirVO);
            }else{
                diarydao.insert(dbhelper,dirVO);
            }


            Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
            //mainintent.putExtra("msg","1");
            setResult(1, mainintent);
            finish();
        });






    }
}
