package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rajendra.vacationtourapp.CnPhu.FullTongQuan;
import com.rajendra.vacationtourapp.HomePage.HienBanDo;
import com.rajendra.vacationtourapp.model.DiaDiem;
import com.rajendra.vacationtourapp.model.NhaNghi;
import com.squareup.picasso.Picasso;

public class ChitietNhaNghi extends AppCompatActivity implements OnMapReadyCallback {
    TextView tv_ten, tv_diachi, tv_tongquan, tv_star, tv_Xemthem, tv_sdt;
    Toolbar tb_title;
    Button bt_lienhe;
    ImageView img_url, image_nen;
    ImageView bt_goidien;
    GoogleMap map;
    String vitri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_nha_nghi);
        anhxa();
        setSupportActionBar(tb_title);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DulieuChitiet();

        tv_Xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChitietNhaNghi.this, FullTongQuan.class);
                intent.putExtra("tongquan", tv_tongquan.getText());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        tv_tongquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChitietNhaNghi.this, FullTongQuan.class);
                intent.putExtra("tongquan", tv_tongquan.getText());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        bt_lienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = tv_sdt.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(ChitietNhaNghi.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_goidien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = tv_sdt.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(ChitietNhaNghi.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhxa() {
        tv_Xemthem = (TextView) findViewById(R.id.xemthem);
        tv_ten = (TextView) findViewById(R.id.tv_ten);
        tv_diachi = (TextView) findViewById(R.id.tv_diachi);
        tv_star = (TextView) findViewById(R.id.tv_star);
        tv_sdt = (TextView) findViewById(R.id.tv_sdt);
        tv_tongquan = (TextView) findViewById(R.id.tv_tongquan);
        image_nen = (ImageView) findViewById(R.id.image_nen);
        bt_goidien = (ImageView) findViewById(R.id.bt_goidien);
        bt_lienhe = (Button) findViewById(R.id.button_lienhe);
        tb_title = (Toolbar) findViewById(R.id.toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        String xy = "21.02809036512912, 105.83395654778238";
        String[] output = vitri.split(",");
        double X, Y;
        X = Double.parseDouble(output[0]);
        Y = Double.parseDouble(output[1]);
        LatLng Hanoi = new LatLng(X, Y);
        map.addMarker(new MarkerOptions().position(Hanoi).title("Nhà riêng"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Hanoi, 15));

        Intent i = new Intent(ChitietNhaNghi.this, HienBanDo.class);
        i.putExtra("dsnn", vitri);
    }


    private void DulieuChitiet() {
        NhaNghi ds = (NhaNghi) getIntent().getSerializableExtra("dsnn");
        tv_ten.setText(ds.getTen());
        tv_diachi.setText(ds.getDiaChi());
        tv_tongquan.setText(ds.getTongQuan());
        tv_sdt.setText(ds.getSdt());
        tv_star.setText(String.valueOf(ds.getDiemDanhGia()));
        tb_title.setTitle(ds.getTen());
        vitri = ds.getVitriMap() + "";
        Picasso.get().load(ds.getHinhAnh()).into(image_nen);
        Toast.makeText(ChitietNhaNghi.this, ds.getVitriMap() + "", Toast.LENGTH_SHORT).show();

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