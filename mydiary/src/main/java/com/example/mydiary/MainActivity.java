package com.example.mydiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DiaryDAO diaryDAO = new DiaryDAO();
        dbhelper = new DBHelper(getApplicationContext());

        ArrayList<DiaryVO> list = DiaryDAO.selectAll(dbhelper);

        //listview 초기화 -> adapter지정 ->
        lv = findViewById(R.id.listDiary);
        //list에 selectAll 리턴 값이 담긴거?
        MyAdapter adapter = new MyAdapter(list);
        lv.setAdapter(adapter);

        // 아이템 클릭 이벤트 : 수정, 삭제
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("확인")
                    .setPositiveButton("수정",(dialogInterface, i1) -> {
                        Intent intent = new Intent(getApplicationContext(), WriteActive.class);

                        String id = list.get(i).getId();
                        String text = list.get(i).getText();
                        String content = list.get(i).getContent();
                        String img = list.get(i).getImg();




                        intent.putExtra("id",id);
                        intent.putExtra("text",text);
                        intent.putExtra("content",content);
                        intent.putExtra("img", img);



                        startActivity(intent);

                        ((MyAdapter)lv.getAdapter()).setData(DiaryDAO.selectAll(dbhelper));
                        ((MyAdapter)lv.getAdapter()).notifyDataSetChanged();

                    })

                    .setNegativeButton("삭제", (dialogInterface, i1) -> {
                        diaryDAO.delete( dbhelper, list.get(i));
                        list.remove(i);
                        ((MyAdapter)lv.getAdapter()).notifyDataSetChanged();

                    })
                    .create()
                    .show();
        });

        //쓰기 버튼 이벤트 지정 : writeActivity로 이동
        Button btnWriteForm = findViewById(R.id.btnWriteForm);
        btnWriteForm.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), WriteActive.class);
            startActivity(intent);
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