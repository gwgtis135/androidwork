package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button button2, btnResult, btnInsert, btnSelectList;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.button2);
        btnResult = findViewById(R.id.btnResult);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelectList = findViewById(R.id.btnSelectList);

        tv = findViewById(R.id.textView2);

        RequestQueue queue = Volley.newRequestQueue(this);

        //전체조회
        btnSelectList.setOnClickListener(view -> {
            String url ="http://192.168.0.26/userList";
            StringRequest request = new StringRequest(url, s->{
                //이름과 패스워드만 표시  .Map, UserVO
                System.out.println(s);
                tv.setText(s);
            }, e->{

                Log.d("request", e.toString());
            });
            queue.add(request);
        });


        //파라미터 보내는 기능
        //마지막 중괄호가 상속받아서 보내ㅐ준다
        btnInsert.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
            startActivity(intent);

        });





    }
}