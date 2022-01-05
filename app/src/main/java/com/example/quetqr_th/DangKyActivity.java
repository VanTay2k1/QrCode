package com.example.quetqr_th;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKyActivity extends AppCompatActivity {
    EditText edtTen,edtMK,edtNLMK;
    Button btnDN,btnDK;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtTen = (EditText) findViewById(R.id.edtTen);
        edtMK = (EditText) findViewById(R.id.edtMatKhau);
        edtNLMK = (EditText) findViewById(R.id.edtNLMK);
        btnDK = (Button) findViewById(R.id.btDKy);

        database = new Database(this, "Qldata", null, 1);
        database.QueryData("Create table if not exists Taikhoan(id integer primary key autoincrement, username text, matkhau text, anh BLOB)");
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtTen.getText().toString().trim();
                String mkhau = edtMK.getText().toString().trim();
                String nlmkhau = edtNLMK.getText().toString().trim();
                if (username.equals("") || mkhau.equals("") || nlmkhau.equals("")){
                    Toast.makeText(DangKyActivity.this, "Tài khoản hoặc mật khẩu không hơp lệ.", Toast.LENGTH_SHORT).show();
                }else {
                    if (mkhau.equals(nlmkhau)){
                        Boolean usercheckR = checkusername(username);
                        if (usercheckR == false){
                            database.QueryData("Insert into Taikhoan(username, matkhau) values('"+ username +"', '"+ mkhau +"')");
                            Toast.makeText(DangKyActivity.this, "Đăng ký thành công.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(DangKyActivity.this, "Tài khoản đã tồn tại.", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(DangKyActivity.this, "Mật khẩu không khớp.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private Boolean checkusername(String username){
        Cursor cursor = database.GetData("Select * from Taikhoan where username = '"+ username +"'");
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}