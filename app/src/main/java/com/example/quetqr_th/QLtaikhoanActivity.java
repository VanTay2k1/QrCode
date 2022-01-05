package com.example.quetqr_th;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Locale;

public class QLtaikhoanActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<taikhoan> arrayList, array;
    taikhoanAdapter adapter;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltaikhoan);

        anhxa();
        arrayList = new ArrayList<>();

        adapter = new taikhoanAdapter(this, R.layout.dong_taikhoan,arrayList);
        listView.setAdapter(adapter);

        database = new Database(this, "Qldata", null, 1);
        database.QueryData("Create table if not exists Taikhoan(id integer primary key autoincrement, username text, matkhau text, anh BLOB)");


        //get data
        Cursor cursor = database.GetData("SELECT * FROM Taikhoan");
        arrayList.clear();
        while (cursor.moveToNext()){
            arrayList.add(new taikhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)

            ));
        }

        adapter.notifyDataSetChanged();

    }

    private void anhxa() {
        listView = (ListView) findViewById(R.id.lisviewtaikhoan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        MenuItem item = menu.findItem(R.id.menutimkiem);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filter(newText.trim());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void filter(String chartext){
        resertlist();
        chartext= chartext.toLowerCase(Locale.getDefault());
        array = new ArrayList<>();
        array.addAll(arrayList);
        arrayList.clear();
        if (chartext.length() == 0){
            arrayList.addAll(array);
        }else {
            for (taikhoan tk : array){
                if (tk.getUsername().toLowerCase(Locale.getDefault()).contains(chartext)){
                    arrayList.add(tk);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
    private void resertlist(){
        Cursor cursor = database.GetData("SELECT * FROM Taikhoan");
        arrayList.clear();
        while (cursor.moveToNext()){
            arrayList.add(new taikhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)

            ));
        }

        adapter.notifyDataSetChanged();
    }
}