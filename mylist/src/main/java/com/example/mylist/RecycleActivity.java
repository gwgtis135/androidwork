package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecycleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MemberVO>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);


        list = new  ArrayList<MemberVO>();//이 부분 다시 보기
        MemberVO memoVO = new MemberVO();
        memoVO.setTitle("java"); memoVO.setContent("java content");
        list.add(memoVO);
        memoVO = new MemberVO();
        memoVO.setTitle("spring"); memoVO.setContent("spring content");
        list.add(memoVO);
        memoVO = new MemberVO();
        memoVO.setTitle("android"); memoVO.setContent("android content");
        list.add(memoVO);

        //3번째가 뷰
        recyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyRecycleAdapter(list));

        //전체 클릭하면 모달뜨게 한다.
        recyclerView.setOnClickListener(view -> {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());//context넣는 자리 ?
            builder.setMessage("아이템이 선택됨").create();
        });


    }
}