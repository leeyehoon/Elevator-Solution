package com.example.wac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

@SuppressLint("CustomSplashScreen")
public class LogoActivity extends AppCompatActivity {
    private  static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        },SPLASH_TIME_OUT);
    }
}