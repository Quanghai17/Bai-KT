package com.example.dienthoai;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtHang, edtMa, edtXuatXu;
    RadioButton rb1,rb2,rb3;
    CheckBox cb1,cb2,cb3;
    ListView listView;
    Button btnAdd, btnClear;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        createData();
      //  Show();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Insert();
                Show();
            }
        });
    }

    private void Show() {
        String key = edtHang.getText().toString();
        String sql = " select * from tbPhone where hang = '"+key+"' " ;
        Cursor cs = database.rawQuery(sql,null);
        while (cs.moveToNext()){
             int id = cs.getInt(0);
             String ten = cs.getString(1);
            String ma = cs.getString(2);
            String xuat = cs.getString(3);
            String loai = cs.getString(4);
            String time = cs.getString(5);

            Toast.makeText(MainActivity.this,"Hang la: "+ ten + "Mã là:" + ma +"Sản xuất: "+xuat,Toast.LENGTH_LONG).show();

        }


    }

    private void createData() {
        database = openOrCreateDatabase("data3",MODE_PRIVATE,null);
        String sql = "create table if not EXISTS tbPhone(id integer primary key,hang text,ma text,xuatxu text,loai text,baohanh text)";
        database.execSQL(sql);
    }

    private void Insert() {
        String hang = edtHang.getText().toString();
        String ma = edtMa.getText().toString();
        String xuatxu = edtXuatXu.getText().toString();
        String loai ="";
        String baohanh = "";
        if(rb1.isChecked()){
            loai = rb1.getText().toString();
        }
        if(rb2.isChecked()){
            loai = rb2.getText().toString();
        }
        if(rb3.isChecked()){
            loai = rb3.getText().toString();
        }

        if(cb1.isChecked()){
            baohanh = cb1.getText().toString();
        }
        if(cb2.isChecked()){
            baohanh += cb2.getText().toString();
        }
        if(cb3.isChecked()){
            baohanh += cb3.getText().toString();
        }
        if(hang.isEmpty() || ma.isEmpty() || xuatxu.isEmpty()){
            Toast.makeText(MainActivity.this,"Đéo đc",Toast.LENGTH_LONG).show();
        }else {
            String sql = "insert into tbPhone(hang,ma ,xuatxu ,loai ,baohanh) values ('"+hang+"', '"+ma+"', '"+xuatxu+"','"+loai+"','"+baohanh+"')";
            database.execSQL(sql);
            Toast.makeText(MainActivity.this,"đc",Toast.LENGTH_LONG).show();
        }

    }

    private void AnhXa() {
        edtHang = findViewById(R.id.edt_hangsx);
        edtMa = findViewById(R.id.edt_mamay);
        edtXuatXu = findViewById(R.id.edt_xuatxu);
        cb1 = findViewById(R.id.checkbox1);
        cb2 = findViewById(R.id.checkbox2);
        cb3 = findViewById(R.id.checkbox3);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        listView = findViewById(R.id.lvTT);
        btnAdd = findViewById(R.id.btn_addShow);
        btnClear = findViewById(R.id.btn_nhaplai);

    }
}