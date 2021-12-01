package com.example.myactive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnDial, btnWeb,btnGoogle,btnSearch, btnSms, btnPhotp, btnLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젝 초기화

        btnDial = findViewById(R.id.btnDial);
        btnWeb = findViewById(R.id.btnWeb);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnSearch = findViewById(R.id.btnSearch);
        btnSms = findViewById(R.id.btnSms);
        btnPhotp = findViewById(R.id.btnPhotp);
        btnLife = findViewById(R.id.btnLife);

        btnLife.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LifeActivity.class);
            startActivityForResult(intent,1);
        });
        btnDial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Uri uri = Uri.parse("tel:010-5771-4801");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);

            }
        });

             btnWeb.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Uri uri = Uri.parse("http://www.google.com");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
        });

             btnSms.setOnClickListener(new View.OnClickListener(){
                 public void onClick(View v){
                     Intent intent = new Intent(Intent.ACTION_SENDTO);
                     intent.putExtra("sms_body", "안녕하세요");
                     intent.setData(Uri.parse("smsto:" + Uri.encode("010-5771-4801")));
                     startActivity(intent);
                 }
             });
             btnPhotp.setOnClickListener(new View.OnClickListener(){
                 public  void onClick(View view){
                 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 startActivity(intent);
                 }
             });
    }
}