package com.example.mydiary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActive extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE =1;
    static final int REQUEST_TAKE_PHOTO =2;
    static final int REQUEST_PHOTO_SELECTION =3;



    Button btnSave, btnImage, btnGallery;
    EditText editTitle, editContent;
    DBHelper dbhelper;
    ImageView imageDiary;
    String id;
    String title;
    String content;
    String currentPhotoPath;
    File photoFile;
    Uri photoURI;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        dbhelper = new DBHelper(getApplicationContext());

        DiaryDAO diarydao = new DiaryDAO();
        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);
        editContent = findViewById(R.id.editContent);
        editTitle = findViewById(R.id.editTitle);
        imageDiary =findViewById(R.id.imageDiary);
        btnGallery = findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(view -> {getPhoto();});

        btnImage.setOnClickListener(view -> { dispatchTakePictureIntent();  });
//        btnPhoto.setOn


        Intent intent = getIntent();

        if(intent.getExtras() !=null) {
            id = intent.getExtras().getString("id");
            title = intent.getExtras().getString("text");
            content = intent.getExtras().getString("content");

            if(intent.getExtras().getString("img") != null){
                String img = intent.getExtras().getString("img");
                Uri photoURI = Uri.parse(img);
                imageDiary.setImageURI(photoURI);
            }


        }

        editTitle.setText(title);
        editContent.setText(content);

        btnSave.setOnClickListener(view -> {
            //입력한 값 어뎁터에
            DiaryVO dirVO = new DiaryVO();
            dirVO.setId(id);

            dirVO.setText(editTitle.getText().toString());
            dirVO.setContent(editContent.getText().toString());
            dirVO.setImg(currentPhotoPath);
            if(intent.getExtras() !=null){
                diarydao.update(dbhelper,dirVO);
            }else{
                diarydao.insert(dbhelper,dirVO);
            }


            Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
            //mainintent.putExtra("msg","1");
            setResult(1, mainintent);
            finish();
        });






    }//onCreate

    private void getPhoto(){
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(photoFile != null){
            photoURI = FileProvider.getUriForFile(this,
                    "com.example.mydiary",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_PHOTO_SELECTION);
        }

    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         photoFile = null;
        try {
            photoFile = createImageFile();
            //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (IOException ex) {}
        // Error occurred while creating the File

        // Continue only if the File was successfully created
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(this,
                    "com.example.mydiary",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    //촬영하지 않고 가져올 때는 bitmap으로 가져온다.
    //직접 가져올 때는 파일명을 만들고 넘겨준다.
    //정해진 경로로 이미지가 저장되고
    //파일명으로 uri를 바꿔주면 된다 ?
    //포토
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageDiary.setImageBitmap(imageBitmap);
        }else if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            imageDiary.setImageURI(photoURI);
        }else if(requestCode == REQUEST_PHOTO_SELECTION && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
           imageDiary.setImageURI(selectedImage);
        }
    }
    public void setPicture(String picturePath, int sampleSize){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        Bitmap resultPhotoBitmap = BitmapFactory.decodeFile(picturePath, options);

        imageDiary.setImageBitmap(resultPhotoBitmap);
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);  //directory_ct sd카드 안에 저장된 위치 안에
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();     //파일 경로이다.
        return image;
    }





}
