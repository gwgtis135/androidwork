package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {
    CheckBox chkagree;
    RadioGroup Rgroup1;
    RadioButton rdoDog;
    RadioButton rdoCat;
    RadioButton rdoRabbit;
//    button;
    ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chkagree = findViewById(R.id.ChkAgree);
        rdoDog = findViewById(R.id.RdoDog);
        imgPet = findViewById(R.id.imgPet);
        Rgroup1 = findViewById(R.id.Rgroup1);


        View.OnClickListener handler = v -> {
          //if switch로 radio 버튼에 따라서 이미지 변경

        switch (Rgroup1.getCheckedRadioButtonId()){

            case R.id.RdoDog: imgPet.setImageResource(R.drawable.dog);

        }

        };
        rdoDog.setOnClickListener(handler);
        rdoCat.setOnClickListener(handler);
        rdoRabbit.setOnClickListener(handler);




        chkagree.setOnClickListener(v->{
            if (chkagree.isChecked()) {

                Rgroup1.setVisibility(View.VISIBLE);

            }else{
                Rgroup1.setVisibility(View.INVISIBLE);
            }
        });
    }
}