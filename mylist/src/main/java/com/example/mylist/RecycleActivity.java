package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecycleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Map<String,String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);


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
        recyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyRecycleAdapter(list));

    }
}