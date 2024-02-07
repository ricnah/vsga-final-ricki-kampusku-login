package com.example.kampusku;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan;
    EditText nomor, nama, tgl, jekel, alamat;
    String edit;
    TextView tvNomor, tvNama, tvTgl, tvJekel, tvAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dat);

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

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
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
                    db.execSQL("INSERT INTO mahasiswa(nomor, nama, tgl, jk, alamat) values('"+
                            nomor.getText().toString() + "','" +
                            nama.getText().toString() + "','" +
                            tgl.getText().toString() + "','" +
                            jekel.getText().toString() + "','" +
                            alamat.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                DataMahasiswa.da.RefreshList();
            }
        });

    }
}