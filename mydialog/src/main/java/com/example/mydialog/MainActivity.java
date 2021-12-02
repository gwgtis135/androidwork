package com.example.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        String[] city = new String[]{"대구", "서울", "부산"};


        //this빌더 안에 넣어서 초기화 시켜 준다.
        btn1.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("제목")
                    .setMessage("alert")
                    .setNegativeButton("삭제", (dialogInterface, i) -> {
                        Log.d("delete", "삭제버튼");
                    })
                    .setPositiveButton("수정", (dialogInterface, i) -> {
                        Log.d("alter", "수정버튼");
                    })

                    .create().show();

        });
        ArrayList selectedItems = new ArrayList<Integer>();
        //setItems인 경우는 하나만 선택이 된다.
        btn2.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMultiChoiceItems(city, null,
                    ( dialog, which,isChecked)-> {
                            if (isChecked) {
                                // If the user checked the item, add it to the selected items
                                selectedItems.add(which);

                            } else if (selectedItems.contains(which)) {
                                // Else, if the item is already in the array, remove it
                                selectedItems.remove(which);
                        }
                    })
                    .setPositiveButton("확인", (dialog, id) ->{
                        //선택된 값들을 city배열에서 찾아서 출력
                        for(Object i:selectedItems){
                            int pos = ((Integer)i).intValue();
                            Log.d("alert",city[pos]);

                        }
                        //city.stream(city).filter().map(System.out::print);
                    })
                    .create()
                    .show();
        });

        //커스텀 다이아로그
        btn3.setOnClickListener(view -> {   customModal();  });
        btn4.setOnClickListener(view -> {});

    }
    //메소드
    private void customModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(R.layout.activity_login)
                .create()
                .show();
    }
}