package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rajendra.vacationtourapp.HomePage.HomePage;

public class DangNhap extends AppCompatActivity {
    Button buttonSingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        init();
        controlDangNhap();
    }

    private void controlDangNhap() {
        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

    void init() {
        buttonSingIn = (Button) findViewById(R.id.buttonSingIn);
    }

}