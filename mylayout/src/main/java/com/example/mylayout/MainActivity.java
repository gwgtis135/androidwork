package com.example.mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridLayout linera;
    int startNum = 1;
    //int startNum = 1;   //처음 시작 1부터 시작할수 있음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        //동적으로
        linera = findViewById(R.id.linear);

        View.OnClickListener handler = v -> {


            //Toast.makeText(this, "클릭됨", Toast.LENGTH_LONG).show();
            //모든 버튼 글릭하면 이쪽으로 오게됨
            //get번호로 찾아서 바꿔주기
            //클릭한 버튼의 text(숫자값)을 읽어서 startNum과 같다면

            int endNum = 0;
            int temp = 0;
            CharSequence clickNum = ((Button)v).getText();
            int clinu = Integer.parseInt(String.valueOf(clickNum));

            if(clinu == startNum){
                //startNum++
                startNum++;
                v.setVisibility(View.INVISIBLE);
                Toast.makeText(this,"Game over",Toast.LENGTH_SHORT).show();

                //((Button) v).setText(' ');
                //16번까지 도착했으면 게임완료 toast
                if(startNum == 17){
                    System.out.println(startNum);
                    Toast.makeText(this,"Game over",Toast.LENGTH_LONG).show();
                }
            }

            //버튼의 text값을 reset
        };
        //1차원 배열 16개의 임의의 순서로 적어주기
        List<Integer> list = new ArrayList<>();

        for (int i =1; i<=16; i++){
            list.add(i);
        }
        Collections.shuffle(list);

        for (int i : list) {
            Button button = new Button(this);
            button.setText(String.valueOf(i));
            linera.addView(button);
            button.setOnClickListener(handler);

        }
    }
}