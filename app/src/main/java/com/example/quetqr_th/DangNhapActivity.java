package com.example.quetqr_th;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtTen,edtMK;
    Button btnDN,btnDK;
    public static int id;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtTen = (EditText) findViewById(R.id.edttenDN);
        edtMK = (EditText) findViewById(R.id.edtMK);
        btnDN = (Button) findViewById(R.id.btDN);
        btnDK = (Button) findViewById(R.id.btDK);

        database = new Database(this, "Qldata", null, 1);
        database.QueryData("Create table if not exists Taikhoan(id integer primary key autoincrement, username text, matkhau text, anh BLOB)");

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = edtTen.getText().toString();
                String pass = edtMK.getText().toString();
                Boolean checkuserpass = checkusernamepassword(user, pass);
                if (checkuserpass == true){
                    layid(user);
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
    public void layid(String username){
        Cursor data = database.GetData("Select id from Taikhoan where username = '"+ username +"'");
        while (data.moveToNext()){
            id= data.getInt(0);
        }
    }
    private Boolean checkusernamepassword(String username, String password){
        Cursor cursor = database.GetData("Select * from Taikhoan where username = '"+ username +"' and matkhau = '"+ password +"'");
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }

    }
}