package com.example.quetqr_th;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public void INSERT(String username, String pass, byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Taikhoan (username, matkhau, anh) VALUES(?, ?, ?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, username);
        sqLiteStatement.bindString(2,pass);
        sqLiteStatement.bindBlob(3, hinh);

        sqLiteStatement.executeInsert();
    }
    public void UPDATE(String username, String pass, byte[] hinh, int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Taikhoan SET username = ? , matkhau = ?, anh = ? WHERE id = '"+ id +"'";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, username);
        sqLiteStatement.bindString(2,pass);
        sqLiteStatement.bindBlob(3, hinh);
        sqLiteStatement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
