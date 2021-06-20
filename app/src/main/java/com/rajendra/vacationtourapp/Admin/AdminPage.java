package com.rajendra.vacationtourapp.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rajendra.vacationtourapp.HomePage.HomePage;
import com.rajendra.vacationtourapp.R;

public class AdminPage extends AppCompatActivity {
    Button bt_qldd, bt_qlnn, bt_qltk, bt_thongke, bt_dangxuat;
    Toolbar tooladmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        Anhxa();
        setSupportActionBar(tooladmin);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bt_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

    private void Anhxa() {
        bt_qldd = (Button) findViewById(R.id.bt_qldd);
        bt_qlnn = (Button) findViewById(R.id.bt_qlnn);
        bt_qltk = (Button) findViewById(R.id.bt_qltk);
        bt_thongke = (Button) findViewById(R.id.bt_thongke);
        bt_dangxuat = (Button) findViewById(R.id.bt_dangxuatadmin);
        tooladmin = (Toolbar) findViewById(R.id.tool_admin);
    }

    public void qldd_onclick(View view) {
        Intent in = new Intent(view.getContext(), Activity_QuanlyDiaDiem.class);
        startActivity(in);
    }

    public void qlnn_onclick(View view) {
        Intent in = new Intent(view.getContext(), Activity_QuanLyNhaNghi.class);
        startActivity(in);
    }

    public void qltk_onclick(View view) {
        Intent in = new Intent(view.getContext(), Activity_QuanLyTaiKhoan.class);
        startActivity(in);
    }

    //
//    public void admin_thongke_onclick(View view) {
//        Intent in = new Intent(view.getContext(), admin_thongke.class);
//        startActivity(in);
//    }
//
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