package com.example.kampusku;

import static com.example.kampusku.LoginActivity.FILENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.File;
public class SplashScreen extends AppCompatActivity {

    private int waktu_loding = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin()) {
                    Intent toHome = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(toHome);
                } else {
                    Intent toLogin = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(toLogin);
                }
                finish();
            }
        }, waktu_loding);
    }

    boolean isLogin() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}