package com.example.kampusku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnUbah;
    EditText nomor, nama, tgl, jekel, alamat;
    String edit;
    TextView tvNomor, tvNama, tvTgl, tvJekel, tvAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dbHelper = new DataHelper(this);

        nomor = (EditText) findViewById(R.id.editTextNomor);
        nama = (EditText) findViewById(R.id.editTextNama);
        tgl = (EditText) findViewById(R.id.editTextTanggalLahir);
        jekel = (EditText) findViewById(R.id.editTextJenisKelamin);
        alamat = (EditText) findViewById(R.id.editTextAlamat);

        tvNomor = (TextView) findViewById(R.id.textViewIsiNomor);
        tvNama = (TextView) findViewById(R.id.textViewIsiNama);
        tvTgl = (TextView) findViewById(R.id.textViewIsiTanggalLahir);
        tvJekel = (TextView) findViewById(R.id.textViewIsiJenisKelamin);
        tvAlamat = (TextView) findViewById(R.id.textViewIsiAlamat);

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

        btnUbah = (Button) findViewById(R.id.btnUbah);
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                edit = nomor.getText().toString();
                edit = nama.getText().toString();
                edit = tgl.getText().toString();
                edit = jekel.getText().toString();
                edit = alamat.getText().toString();
                if (edit.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put("nama", nama.getText().toString());
                    cv.put("tgl", tgl.getText().toString());
                    cv.put("jk", jekel.getText().toString());
                    cv.put("alamat", alamat.getText().toString());

                    db.update("mahasiswa", cv, "nomor = '" + nomor.getText().toString() + "'", null);

                    Toast.makeText(getApplicationContext(), "Data berhasil diubah!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                DataMahasiswa.da.RefreshList();
            }
        });
    }
}