package com.example.quetqr_th;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CapNhatCaNhanActivity extends AppCompatActivity {

    DangNhapActivity login;
    Database database;
    ImageView imageView;
    ImageButton imageButtoncam, imageButtonfile, imgbtback;
    EditText editTextuser, editTextpass, editTextnlpass;
    Button buttonluu;
    int CAMERA = 123, FILE = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_ca_nhan);

       anhxa();

        database = new Database(this, "Qldata", null, 1);
        database.QueryData("Create table if not exists Taikhoan(id integer primary key autoincrement, username text, matkhau text, anh BLOB)");
        xuat(login.id);

        imgbtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CapNhatCaNhanActivity.this, CaNhanActivity.class));
            }
        });
        buttonluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyen data sang byte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhanh = byteArrayOutputStream.toByteArray();
                database.UPDATE(editTextuser.getText().toString().trim(), editTextpass.getText().toString().trim(), hinhanh, login.id);
                startActivity(new Intent(CapNhatCaNhanActivity.this, CaNhanActivity.class));
            }
        });
        imageButtonfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, FILE);
            }
        });
        imageButtoncam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode ==CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
        if(requestCode ==FILE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void xuat(int id){
        Cursor data = database.GetData("Select * from Taikhoan where id = '"+ id +"'");
        while (data.moveToNext()){
            editTextuser.setText(data.getString(1));
            editTextpass.setText(data.getString(2));
            editTextnlpass.setText(data.getString(2));
            byte[] hinhanh = data.getBlob(3);
            if(hinhanh !=null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
                imageView.setImageBitmap(bitmap);
            }

        }


    }

    private void anhxa() {
        imageView = findViewById(R.id.imvanh);
        imageButtoncam = findViewById(R.id.cam);
        imageButtonfile = findViewById(R.id.file);
        editTextuser = findViewById(R.id.edt_update_user);
        editTextpass = findViewById(R.id.edt_update_pass);
        editTextnlpass = findViewById(R.id.edt_update_repass);
        buttonluu = findViewById(R.id.btnHoanTat);
        imgbtback = findViewById(R.id.comeback);
    }
}