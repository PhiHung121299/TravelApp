package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rajendra.vacationtourapp.HomePage.TravelLocation;
import com.rajendra.vacationtourapp.model.TopPlacesData;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView tv_ten, tv_diachi, tv_tongquan, tv_star;
    Toolbar tb_title;
    ImageView img_url, image_nen;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        anhxa();
        Dulieu();
    }
    private void Dulieu() {
        Intent intent = this.getIntent();
        String thongtinchitiet1 = intent.getStringExtra("thongtinchitiet1");
        String thongtinchitiet2 = intent.getStringExtra("thongtinchitiet2");
        String thongtinchitiet3 = intent.getStringExtra("thongtinchitiet3");
        String thongtinchitiet4 = intent.getStringExtra("thongtinchitiet4");
        String tongquan = intent.getStringExtra("tongquan");
        Toast.makeText(DetailsActivity.this, tongquan + "/" + thongtinchitiet1 + "/" + thongtinchitiet2 + "/" + thongtinchitiet3 + "/" + thongtinchitiet4, Toast.LENGTH_SHORT).show();
        tv_ten.setText(thongtinchitiet1);
        tv_diachi.setText(thongtinchitiet2);
        tv_tongquan.setText(tongquan);
        tv_star.setText(String.valueOf(thongtinchitiet4));
        tb_title.setTitle(thongtinchitiet1);
        Picasso.get().load(thongtinchitiet3).into(img_url);
        Picasso.get().load(thongtinchitiet3).into(image_nen);
    }

    private void anhxa() {
        tv_ten = (TextView) findViewById(R.id.tv_ten);
        tv_diachi = (TextView) findViewById(R.id.tv_diachi);
        tv_star = (TextView) findViewById(R.id.tv_star);
        tv_tongquan = (TextView) findViewById(R.id.tv_tongquan);
        img_url = (ImageView) findViewById(R.id.img_url);
        image_nen = (ImageView) findViewById(R.id.image_nen);
        tb_title = (Toolbar) findViewById(R.id.toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        String xy = "21.02809036512912, 105.83395654778238";
        String[] output = xy.split(",");
        double X, Y;
        X = Double.parseDouble(output[0]);
        Y = Double.parseDouble(output[1]);
        LatLng Hanoi = new LatLng(X, Y);
        map.addMarker(new MarkerOptions().position(Hanoi).title("Nhà riêng"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Hanoi, 15));
    }
}
