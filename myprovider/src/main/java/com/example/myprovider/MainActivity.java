package com.example.myprovider;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    Button btnCall, btnContect, btnIns;
    EditText editCall, editName, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 넣어 주는 곳
//        ActivityCompat.requestPermissions(this,
//                new String[]{Manifest.permission.READ_CALL_LOG
//                        ,Manifest.permission.READ_CONTACTS}, MODE_PRIVATE);



        btnContect = findViewById(R.id.btnContect);
        editCall = findViewById(R.id.editCall);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        btnCall = findViewById(R.id.btnCall);
        btnIns = findViewById(R.id.btnIns);




        btnCall.setOnClickListener(view -> {editCall.setText(getCallHistory()); });
        btnContect.setOnClickListener(view -> {editCall.setText(getContacts()); });
        btnIns.setOnClickListener(view -> { addContact();  });
    }
    //oncreate

    public void addContact(){
        // Creates a new Intent to insert a contact
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
// Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, editEmail.getText())
        .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE,
                        ContactsContract.CommonDataKinds.Email.TYPE_WORK)
        .putExtra(ContactsContract.Intents.Insert.PHONE, "222-44444")
        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,
                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
        .putExtra(ContactsContract.Intents.Insert.DATA, editName.getText());

        startActivity(intent);

    }

    public String getCallHistory() {
        String[] callSet = new String[]
                {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.LOOKUP_KEY,
                        Build.VERSION.SDK_INT
                                >= Build.VERSION_CODES.HONEYCOMB ?
                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                                ContactsContract.Contacts.DISPLAY_NAME

                };


        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI,
                callSet, null, null, null); //sortOrder 정렬


        if (c == null)
            return "통화기록 없음";

        StringBuffer callBuff = new StringBuffer(); // 최대 100 통화 저장
        callBuff.append("\n날짜 : 구분 : 전화번호 : 통화시간\n\n");
        c.moveToFirst();
        do {
            long callDate = c.getLong(0);   //날짜를 읽는다.
            SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = datePattern.format(new Date(callDate));
            callBuff.append(date_str + ":");        //StringBuffer에 append
            if (c.getInt(1) == CallLog.Calls.INCOMING_TYPE) //착신 발신 읽어내기
                callBuff.append("착신 :");
            else
                callBuff.append("발신 :");
            callBuff.append(c.getString(2) + ":");      //String buffer에 append
            callBuff.append(c.getString(3) + "초\n");
        } while (c.moveToNext());       //cursor 수 많큼 돌기

        c.close();
        return callBuff.toString();
    }



    public String getContacts() {
        String[] callSet = new String[]{

                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.LOOKUP_KEY,
                Build.VERSION.SDK_INT
                        >= Build.VERSION_CODES.HONEYCOMB ?
                        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                        ContactsContract.Contacts.DISPLAY_NAME,

        };




        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                callSet, null, null, null); //sortOrder 정렬

        //nullpoint 체크
        if (c == null)
            return "연착처 없음";

        StringBuffer callBuff = new StringBuffer(); // 최대 100 통화 저장
        callBuff.append("\n아이디 : 검색키 : 이름 \n\n");
        c.moveToFirst();
        do {
            callBuff.append(c.getString(0)+":");
            callBuff.append(c.getString(1)+":");
            callBuff.append(c.getString(2)+"\n");
        } while (c.moveToNext());       //cursor 수 많큼 돌기

        c.close();
        return callBuff.toString();
    }




//    @Override
//    public void onDenied(int i, String[] strings) { }
//    @Override
//    public void onGranted(int i, String[] strings) { }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//    { super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
//    }
}