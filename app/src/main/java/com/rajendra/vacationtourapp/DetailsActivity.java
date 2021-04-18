package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rajendra.vacationtourapp.HomePage.TravelLocation;
import com.rajendra.vacationtourapp.model.TopPlacesData;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailsActivity extends AppCompatActivity {
    TextView tv_ten, tv_diachi, tv_tongquan, tv_star;
    Toolbar tb_title;
    ImageView img_url, image_nen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tv_ten = (TextView) findViewById(R.id.tv_ten);
        tv_diachi = (TextView) findViewById(R.id.tv_diachi);
        tv_star = (TextView) findViewById(R.id.tv_star);
        tv_tongquan = (TextView) findViewById(R.id.tv_tongquan);
        img_url = (ImageView) findViewById(R.id.img_url);
        image_nen = (ImageView) findViewById(R.id.image_nen);
        tb_title = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = this.getIntent();
        String thongtinchitiet1 = intent.getStringExtra("thongtinchitiet1");
        String thongtinchitiet2 = intent.getStringExtra("thongtinchitiet2");
        String thongtinchitiet3 = intent.getStringExtra("thongtinchitiet3");
        String thongtinchitiet4 = intent.getStringExtra("thongtinchitiet4");
        String tongquan = intent.getStringExtra("tongquan");
        Toast.makeText(DetailsActivity.this, tongquan + "/" + thongtinchitiet1 + "/" + thongtinchitiet2 + "/" + thongtinchitiet3 + "/" + thongtinchitiet4, Toast.LENGTH_SHORT).show();
        getdataChitietSP();
        tv_ten.setText(thongtinchitiet1);
        tv_diachi.setText(thongtinchitiet2);
        tv_tongquan.setText(tongquan);
        tv_star.setText(String.valueOf(thongtinchitiet4));
        tb_title.setTitle(thongtinchitiet1);
        Picasso.get().load(thongtinchitiet3).into(img_url);
        Picasso.get().load(thongtinchitiet3).into(image_nen);

    }

    private void getdataChitietSP() {


    }
}
