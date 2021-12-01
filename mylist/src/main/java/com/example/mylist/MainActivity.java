package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btnnsel;
    ArrayList<Map<String,String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //main 엑티비티 보여주기
        lv = findViewById(R.id.lv);
        btnnsel = findViewById(R.id.btnnsel);

        //메모

        list = new ArrayList<Map<String, String>>();//이 부분 다시 보기
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "홍길동"); map.put("addr","대구");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name", "김김동"); map.put("addr","서울");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name", "강감찬"); map.put("addr","부산");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name", "기영이"); map.put("addr","마산");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name", "잔다르크"); map.put("addr","네덜란드");
        list.add(map);
        //3번째가 뷰
        MyAdapter adapter = new MyAdapter(list);  //MyAdapter 생성자 list를 담기
        lv.setAdapter(adapter);

        //this 밑에 익명 객체를 가르킨다.
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getApplicationContext(), list.get(i).get("addr"), Toast.LENGTH_SHORT).show();

        });


    }//oncreate
}