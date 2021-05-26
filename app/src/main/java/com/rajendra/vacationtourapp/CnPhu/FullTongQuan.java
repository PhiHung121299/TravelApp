package com.rajendra.vacationtourapp.CnPhu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.rajendra.vacationtourapp.R;

public class FullTongQuan extends AppCompatActivity {
    TextView tv_fulltongquan;
    String tongquan;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_tong_quang);

        anhxa();
        toolbar = (Toolbar) findViewById(R.id.toolbar_fulltongquan);
        toolbar.setTitle("Tổng quan");
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        tongquan = intent.getStringExtra("tongquan");
        tv_fulltongquan.setText(tongquan);
    }

    private void anhxa() {
        tv_fulltongquan = (TextView) findViewById(R.id.tv_Fulltongquan);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}