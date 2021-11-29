package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    //전역 변수 선언자리 (어느 메소드나 사용가능)
    Button btnp, btnmi, btnmu, btndi ;
    EditText txtNum1, txtNum2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //onCreate 생성될 때를 의미
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_calc);

        btnp = findViewById(R.id.plus);
        btnmi = findViewById(R.id.minus);
        btnmu = findViewById(R.id.mul);
        btndi = findViewById(R.id.divide);

        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        tv = findViewById(R.id.textView3);
        //System.out.println(R.string.c1);

        //익명클래스 ( 클래스 선언과 생성을 동시에 한다.)
        //람다식 (하나의 추상메서드만 있는 인터페이스인 경우)
//        View.OnClickListener handler = view -> {
//            System.out.println("람다로 표현");
//            //Toast.makeText(null, "클릭됨!!!", Toast.LENGTH_LONG);
//
//            //계산 editText 입력값들을 더해서 textview에 출력
//
//        };

//        btnp.setOnClickListener(v->{
//           String n1 = txtNum1.getText().toString();
//           String n2 = txtNum2.getText().toString();
//           int result = Integer.parseInt(n1) + Integer.parseInt(n2);
//           tv.setText(String.valueOf(result));
//
//        });

        View.OnClickListener handler = v ->{

            double n1 = Double.parseDouble(txtNum1.getText().toString());
            double n2 = Double.parseDouble(txtNum2.getText().toString());
            double result =0.0;
//            Button button = (Button) v;
//            String clickValue = button.getText().toString();
//            System.out.println(clickValue);
            switch (v.getId()){
                case R.id.plus: result = n1 + n2; break;
                case R.id.minus: result = n1 - n2; break;
                case R.id.mul: result = n1 * n2; break;
                case R.id.divide: result = n1 / n2; break;
            }

            tv.setText(String.valueOf(result));
        };
        btnp.setOnClickListener(handler);
        btnmi.setOnClickListener(handler);
        btnmu.setOnClickListener(handler);
        btndi.setOnClickListener(handler);

    }
    //innerClass
//    class ClickHandler implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(null, "클릭!!!", Toast.LENGTH_LONG);
//
//        }
//    }
}


