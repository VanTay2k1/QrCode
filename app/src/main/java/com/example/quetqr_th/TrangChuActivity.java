package com.example.quetqr_th;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChuActivity extends AppCompatActivity {
    Button btnscan;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        btnscan = findViewById(R.id.btnScan);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.Trangchu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.Thongbao:
                        startActivity(new Intent(getApplicationContext(),ThongBaoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Trangchu:
                        return true;
                    case R.id.Canhan:
                        startActivity(new Intent(getApplicationContext(),CaNhanActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        scanQRcode();

    }

    private void scanQRcode() {
        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrangChuActivity.this , ScanQRCodeActivity.class));
            }
        });
    }
}