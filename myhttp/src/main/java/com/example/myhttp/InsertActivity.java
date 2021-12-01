package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InsertActivity extends AppCompatActivity {
    Button btnInsertb, btnMain;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        btnInsertb = findViewById(R.id.btnInsertb);
        btnMain = findViewById(R.id.btnMain);
        tv = findViewById(R.id.btnInsertb);

        RequestQueue queue = Volley.newRequestQueue(this);

        //메인페이지 이동
        btnMain.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        //등록
        btnInsertb.setOnClickListener(view -> {
            String url = "http://192.168.0.26/insertUser";
            StringRequest request = new StringRequest(Request.Method.POST,url, s->{
                System.out.println(s);
                tv.setText(s);
            }, e->{}){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id","www");
                    map.put("password", "444");
                    map.put("name", "임꺽정");
                    map.put("role", "admin");
                    return map;
                }
            };
            queue.add(request);
        });
    }
}