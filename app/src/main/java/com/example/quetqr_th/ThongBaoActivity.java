package com.example.quetqr_th;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThongBaoActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView tbuser;
    Database database;
    DangNhapActivity login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        anhxa();
        bottomNavigationview();

        database = new Database(this, "Qldata", null, 1);
        database.QueryData("Create table if not exists Taikhoan(id integer primary key autoincrement, username text, matkhau text, anh BLOB)");
        xuat(login.id);

    }

    private void bottomNavigationview() {
        bottomNavigationView.setSelectedItemId(R.id.Thongbao);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.Thongbao:
                        return true;
                    case R.id.Trangchu:
                        startActivity(new Intent(getApplicationContext(),TrangChuActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Canhan:
                        startActivity(new Intent(getApplicationContext(),CaNhanActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void xuat(int id){
        Cursor data = database.GetData("Select * from Taikhoan where id = '"+ id +"'");
        while (data.moveToNext()){
            tbuser.setText(data.getString(1));
        }
    }

    private void anhxa() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        tbuser = findViewById(R.id.tv_usertb);
    }
}