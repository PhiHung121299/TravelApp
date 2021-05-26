package com.rajendra.vacationtourapp.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;

public class HienBanDo extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    String vitri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_ban_do);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapViewFull);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent = this.getIntent();
        vitri = intent.getStringExtra("vitri");
        map = googleMap;
        String[] output = vitri.split(",");
        double X, Y;
        X = Double.parseDouble(output[0]);
        Y = Double.parseDouble(output[1]);
        LatLng Hanoi = new LatLng(X, Y);
        map.addMarker(new MarkerOptions().position(Hanoi).title("Nhà riêng"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Hanoi, 15));

    }
}