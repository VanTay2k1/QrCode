package com.example.quetqr_th;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CaNhanActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    DangNhapActivity login;
    Database database;
    ImageButton imgBack, imgSua;
    ImageView imgvanh;
    TextView tvUser, tvPass;
    Button btndangxuat, btnqltaikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_nhan);

        anhxa();
        bottomNavigation();
        dangxuat();
        quanlytaikhoan();

        database = new Database(this, "Qldata", null, 1);
        database.QueryData("Create table if not exists Taikhoan(id integer primary key autoincrement, username text, matkhau text, anh BLOB)");
        xuat(login.id);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaNhanActivity.this, TrangChuActivity.class));
            }
        });
        imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaNhanActivity.this, CapNhatCaNhanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void quanlytaikhoan() {
        btnqltaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = tvUser.getText().toString();
                if(user.equals("admin")){
                    startActivity(new Intent(CaNhanActivity.this,QLtaikhoanActivity.class));
                }else {
                    Toast.makeText(CaNhanActivity.this, "Bạn không có quyền truy cập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dangxuat() {
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaNhanActivity.this, DangNhapActivity.class));
            }
        });

    }

    public void xuat(int id){
        Cursor data = database.GetData("Select * from Taikhoan where id = '"+ id +"'");
        while (data.moveToNext()){
            tvUser.setText(data.getString(1));
            tvPass.setText(data.getString(2));
            byte[] hinhanh = data.getBlob(3);
            if(hinhanh !=null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
                imgvanh.setImageBitmap(bitmap);
            }

        }
    }

    private void bottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.Canhan);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.Thongbao:
                        startActivity(new Intent(getApplicationContext(),ThongBaoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Trangchu:
                        startActivity(new Intent(getApplicationContext(),TrangChuActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Canhan:
                        return true;
                }
                return false;
            }
        });
    }

    private void anhxa() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        imgBack = findViewById(R.id.Back);
        imgSua = findViewById(R.id.imgSua);
        imgvanh = findViewById(R.id.idanh);
        tvUser = findViewById(R.id.tv_user);
        tvPass = findViewById(R.id.tv_pass);
        btndangxuat = findViewById(R.id.Dangxuat);
        btnqltaikhoan = findViewById(R.id.QLTaikhoan);
    }
}