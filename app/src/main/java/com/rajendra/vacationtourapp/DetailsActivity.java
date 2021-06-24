package com.rajendra.vacationtourapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.Admin.AdminPage;
import com.rajendra.vacationtourapp.CnPhu.FullTongQuan;
import com.rajendra.vacationtourapp.CnPhu.TimKiem;
import com.rajendra.vacationtourapp.CnPhu.UpLoadAnh;
import com.rajendra.vacationtourapp.HomePage.HienBanDo;
import com.rajendra.vacationtourapp.HomePage.HomePage;
import com.rajendra.vacationtourapp.Login_Registration.LoginActivity;
import com.rajendra.vacationtourapp.adapter.TopDiaDiemAdapter;
import com.rajendra.vacationtourapp.adapter.adapter_Image;
import com.rajendra.vacationtourapp.model.DiaDiem;
import com.rajendra.vacationtourapp.model.HinhAnh;
import com.rajendra.vacationtourapp.model.ImageObject;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView tv_ten, tv_diachi, tv_tongquan, tv_star, tv_Xemthem;
    Toolbar tb_title;
    ImageView img_url1, img_url2, img_url3, image_nen, bt_taianhlen;
    RecyclerView lv_anh;
    GoogleMap map;
    DiaDiem diaDiem;
    HinhAnh ha;
    String vitri;
    DatabaseReference myData, mhinhanh;
    ArrayList<ImageObject> dsData;
    ArrayList<HinhAnh> dsHinhAnh;
    FirebaseAuth mAuth;
    String id;
    LinearLayout linearLayout_upAnh;
    adapter_Image adapter_image;
    ImageObject images;
    ImageSlider imageSlider;
    String nodekey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        anhxa();

        dsData = new ArrayList<ImageObject>();
        images = new ImageObject();
        mAuth = FirebaseAuth.getInstance();
        checkAccount();
        setSupportActionBar(tb_title);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Dulieu();
        //  LoadListAnh();
        DulieuChitiet();

        LoadListAnh();
        XemTongQuan();
        bt_taianhlen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, UpLoadAnh.class);
                intent.putExtra("nodekey", nodekey);

                startActivity(intent);
            }
        });
    }

    private void XemTongQuan() {
        tv_Xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, FullTongQuan.class);
                intent.putExtra("tongquan", tv_tongquan.getText());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        tv_tongquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, FullTongQuan.class);
                intent.putExtra("tongquan", tv_tongquan.getText());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    private void LoadListAnh() {
        List<String> list = new ArrayList<>();
        final FirebaseUser user = mAuth.getCurrentUser();
        myData = FirebaseDatabase.getInstance().getReference().child("TravelLocation");

        dsHinhAnh = new ArrayList<HinhAnh>();
        myData.orderByChild("id").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        nodekey = data.getKey();

                        //
                        Log.i("data", "------------===========>>>>>>" + nodekey + ">>.>>>>>>>");
                        int count = (int) data.child("img").getChildrenCount();

                        mhinhanh = FirebaseDatabase.getInstance().getReference().child("TravelLocation").child("" + nodekey).child("img");

                        mhinhanh.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    HinhAnh ha = dataSnapshot.getValue(HinhAnh.class);
                                    dsHinhAnh.add(ha);
                                }
                                if (dsHinhAnh != null) {
                                    // Log.i("Hinh anh", "----------------------------" + dsHinhAnh.get(0).getUrl());

                                    List<SlideModel> slideModels = new ArrayList<>();
                                    for (int i = 0; i < count; i++) {
                                        if (i <= count - 1) {
                                            slideModels.add(new SlideModel(dsHinhAnh.get(i).getUrl()));
                                            imageSlider.setImageList(slideModels, true);
                                        }
                                    }
                                } else {
                                    Log.i("Danh sách rỗng", "----------------------------" + dsHinhAnh.size());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


//                        for (int i = 1; i <= count; i++) {
//                            if (i <= count) {
//                                //FirebaseAuth.getInstance().getCurrentUser().getUid()
//                                list.add(String.valueOf(data.child("img").child("" + i).child("url").getValue()));
//                                //  list.add(data.child("img").getValue(HinhAnh.class));
//                                //    String[] output = list.get(0).split('={url=');
//                                //   list.add(String.valueOf(data.child("img").child(FirebaseDatabase.getInstance().getReference().child("img").getKey()).child("url").getValue()));
//                            }
//                        }
                        Log.i("data", "----------------------------" + dsHinhAnh.toString() + count);


//                        slideModels.add(new SlideModel(list.get(1)));
//                        slideModels.add(new SlideModel(list.get(2)));

                        //    Log.e("DEBUG", "--------------------------" + diaDiem.getImg().getImage2());
//
//                        Picasso.get().load(list.get(0)).into(img_url1);
//                        Picasso.get().load(list.get(1)).into(img_url2);
//                        Picasso.get().load(list.get(2)).into(img_url3);
                    }

                } else {
                    Log.i("data", "----------------------------" + list.toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void DulieuChitiet() {
        DiaDiem ds = (DiaDiem) getIntent().getSerializableExtra("dsdd");
        tv_ten.setText(ds.getTitle());
        tv_diachi.setText(ds.getLocation());
        tv_tongquan.setText(ds.getTongQuan());
        tv_star.setText(String.valueOf(ds.getStarRating()));
        tb_title.setTitle(ds.title);
        vitri = ds.getVitri() + "";
        id = ds.getId() + "";
        //    Picasso.get().load(ds.getImageUrl()).into(img_url3);
        Picasso.get().load(ds.getImageUrl()).into(image_nen);
        Toast.makeText(DetailsActivity.this, ds.getVitri() + "", Toast.LENGTH_SHORT).show();

    }

    private void anhxa() {
        tv_Xemthem = (TextView) findViewById(R.id.xemthem);
        tv_ten = (TextView) findViewById(R.id.tv_ten);
        tv_diachi = (TextView) findViewById(R.id.tv_diachi);
        tv_star = (TextView) findViewById(R.id.tv_star);
        tv_tongquan = (TextView) findViewById(R.id.tv_tongquan);
//        img_url1 = (ImageView) findViewById(R.id.img_url1);
//        img_url2 = (ImageView) findViewById(R.id.img_url2);
//        img_url3 = (ImageView) findViewById(R.id.img_url3);
        bt_taianhlen = (ImageView) findViewById(R.id.bt_taianhlen);
        linearLayout_upAnh = (LinearLayout) findViewById(R.id.linear_UpAnh);
        image_nen = (ImageView) findViewById(R.id.image_nen);
        tb_title = (Toolbar) findViewById(R.id.toolbar);
        //lv_anh = (RecyclerView) findViewById(R.id.recyclerview);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);


        imageSlider = findViewById(R.id.slider);
        Intent intent = getIntent();
        diaDiem = (DiaDiem) intent.getSerializableExtra("dsdd");
        //   List<SlideModel> slideModels = new ArrayList<>();
        //    slideModels.add(new SlideModel(diaDiem.getImg().getImage1()));
        // slideModels.add(new SlideModel(diaDiem.getImg().getImage2()));
        //  imageSlider.setImageList(slideModels, true);
        //   Log.e("DEBUG", "--------------------------" + diaDiem.getImg().getImage2());


    }

    private void checkAccount() {
        final FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(user.getUid()).child("loai").getValue(String.class).equals("admin")) {
                    linearLayout_upAnh.setVisibility(View.VISIBLE);
                    Log.i("data", "#############################" + " " + user.getEmail());

                } else if (dataSnapshot.child(user.getUid()).child("loai").getValue(String.class).equals("user")) {
                    linearLayout_upAnh.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
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

        Intent i = new Intent(DetailsActivity.this, HienBanDo.class);
        i.putExtra("dsdd", vitri);
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
