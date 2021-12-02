package com.example.myfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText edtDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRead, btnWrite;
        DatePicker dp;



        dp = (DatePicker) findViewById(R.id.datePicker);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        //edtDiary = findViewById(R.id.edtDiary);



        dp.init(2021,11,2, (view, i, i1, i2) -> {
            //년월일로 파일명을 만들고 "20211205.txt"
            //파일을 읽어서 editview에 보이도록 함
            //없으면 toast로 없다면 출력

            i1 = i1+1;
            String fileName = ""+i
                    +((i1<10) ? ("0"+i1):i1)
                    +(i2<10) ? ("0"+i2):i2) + ".txt";
            fileRead(fileName);
        });

        btnWrite.setOnClickListener(view -> {
            try {
                dp.getYear();


                FileOutputStream outFs = openFileOutput("file.txt", Context.MODE_PRIVATE);
                String str = "쿡북 안드로이드";
                outFs.write(str.getBytes());
                outFs.close();
                Toast.makeText(getApplicationContext(), "file.txt.가 생성됨", Toast.LENGTH_SHORT).show();

            }catch (IOException e){ }
        });






        };
    private void fileRead(String filename){
        try {
            edtDiary.setText("");
            FileInputStream inFs = openFileInput(filename);
            byte[] txt = new byte[30];
            inFs.read(txt);
            String str = new String(txt);
            //editview
            edtDiary.setText(str);
            inFs.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "파일 없음", Toast.LENGTH_SHORT).show();
        }

    }
    }
