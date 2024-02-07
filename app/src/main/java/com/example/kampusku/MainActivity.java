package com.example.kampusku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "login";

    Button btnDataMahasiswa;
    Button btnInputData;
    Button btnInformsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDataMahasiswa = findViewById(R.id.btnLihat);
        btnDataMahasiswa.setOnClickListener(view -> {
            Intent toData = new Intent(MainActivity.this, DataMahasiswa.class);
            startActivity(toData);
        });

        btnInputData = findViewById(R.id.btnInput);
        btnInputData.setOnClickListener(view -> {
            Intent toInput = new Intent(MainActivity.this, InputData.class);
            startActivity(toInput);
        });

        btnInformsi = findViewById(R.id.btnInfo);
        btnInformsi.setOnClickListener(view -> {
            Intent toInfo = new Intent(MainActivity.this, Informasi.class);
            startActivity(toInfo);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLogoutClick(MenuItem item) {
        tampilkanDialogKonfirmasiLogout();
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda Yakin Ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    hapusFile();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}