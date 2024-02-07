package com.example.kampusku;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    TextView nomor, nama, tgl, jekel, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        dbHelper = new DataHelper(this);
        nomor = (TextView) findViewById(R.id.textViewIsiNomor);
        nama = (TextView) findViewById(R.id.textViewIsiNama);
        tgl = (TextView) findViewById(R.id.textViewIsiTanggalLahir);
        jekel = (TextView) findViewById(R.id.textViewIsiJenisKelamin);
        alamat = (TextView) findViewById(R.id.textViewIsiAlamat);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nomor.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            tgl.setText(cursor.getString(2).toString());
            jekel.setText(cursor.getString(3).toString());
            alamat.setText(cursor.getString(4).toString());
        }
    }
}