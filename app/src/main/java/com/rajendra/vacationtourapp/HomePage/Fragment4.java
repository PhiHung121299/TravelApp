package com.rajendra.vacationtourapp.HomePage;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.Admin.AdminPage;
import com.rajendra.vacationtourapp.Admin.admin_themtaikhoan;
import com.rajendra.vacationtourapp.CnPhu.ThayAnhDaiDien;
import com.rajendra.vacationtourapp.DuphongDangNhap;
import com.rajendra.vacationtourapp.Login_Registration.LoginActivity;
import com.rajendra.vacationtourapp.Login_Registration.ProfileActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.UserOject;
import com.rajendra.vacationtourapp.model.Nguoidung;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Fragment4 extends Fragment {

    //CardView bt_suaaanh;
    FirebaseAuth mAuth;
    DatabaseReference myData;
    TextView tv_diachi, tv_sdt, tv_hoten, tv_mail, tv_ngaysinh, tv_tentaikhoan;
    String trangthai = "false";
    String checkloaitk;
    Button dangnhap, dangxuat, trangquanly;
    public static List<Nguoidung> thongtin = new ArrayList<>();
    private static SharedPreferences luutaikhoan;
    ImageView img_url, bt_suaanh;
    String id;
    ArrayList<UserOject> dsData;
    private static final int CHOOSE_IMAGE = 101;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment4, container, false);
        tv_diachi = (TextView) view.findViewById(R.id.tv_diachi);
        tv_sdt = (TextView) view.findViewById(R.id.tv_sdt);
        tv_mail = (TextView) view.findViewById(R.id.tv_gmail);
        tv_hoten = (TextView) view.findViewById(R.id.tv_hotennguoidung);
        tv_ngaysinh = (TextView) view.findViewById(R.id.tv_ngaysinh);
        dangnhap = (Button) view.findViewById(R.id.bt_dangnhap);
        bt_suaanh = (ImageView) view.findViewById(R.id.bt_suaanh);
        trangquanly = (Button) view.findViewById(R.id.dentrangquanly);
        dangxuat = (Button) view.findViewById(R.id.bt_dangxuat);
        img_url = (ImageView) view.findViewById(R.id.image_avata);

        mAuth = FirebaseAuth.getInstance();
        dsData = new ArrayList<UserOject>();
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });
        Checkdatadangnhap();
        loadUserInformation();
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                dsData.clear();
            }
        });
        bt_suaanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Ok", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ProfileActivity.class));

            }
        });
        return view;
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        getActivity().finish();
        startActivity(new Intent(getActivity(), HomePage.class));
    }

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {

            id = user.getUid();
            String gm = user.getEmail();
            myData = FirebaseDatabase.getInstance().getReference().child("Users");
            myData.orderByChild("gMail").equalTo(gm).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        UserOject top = snapshot.getValue(UserOject.class);
                        dsData.add(top);
                    }
                    tv_hoten.setText(String.valueOf(dsData.get(0).getHoTen()));
                    tv_ngaysinh.setText(String.valueOf(dsData.get(0).getNgaySinh()));
                    tv_diachi.setText(String.valueOf(dsData.get(0).getDiaChi()));
                    tv_sdt.setText(String.valueOf(dsData.get(0).getsDt()));
                    checkloaitk = String.valueOf(dsData.get(0).getLoai());
                    Picasso.get().load(dsData.get(0).getAnhDaiDien()).into(img_url);
                    Log.i("data", String.valueOf(dataSnapshot.getValue()));
                    checkloaitk();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            // Log.i("data", "#############################" + " " + user.getEmail());

        }

        if (user != null) {
            dangnhap.setVisibility(View.GONE);
            dangxuat.setVisibility(View.VISIBLE);
            if (user.getPhotoUrl() != null) {
//                Glide.with(getActivity())
//                        .load(user.getPhotoUrl().toString())
//                        .into(img_url);

            }
            //

            //
            if (user.getUid() != null) {
                tv_mail.setText(user.getEmail());

            }

            if (user.isEmailVerified()) {
                tv_mail.setText("Email Verified");
            } else {
//                textView.setText("Email Not Verified (Click to Verify)");
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(getActivity(), "Verification Email Sent" , Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
            }
        }
    }

    private void checkloaitk() {
        if (checkloaitk.equals("admin")) {
            trangquanly.setVisibility(View.VISIBLE);
            trangquanly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AdminPage.class);
                    startActivity(intent);
                }
            });
        } else {
            trangquanly.setVisibility(View.INVISIBLE);
        }
    }

    private void checkdata() {
        Nguoidung ds = (Nguoidung) getActivity().getIntent().getSerializableExtra("thongtin");
        String TenTk = ds.getHoTen() + "";
        if (!TextUtils.isEmpty(TenTk)) {
            dangnhap.setVisibility(View.GONE);
            dangxuat.setVisibility(View.VISIBLE);
            tv_tentaikhoan.setText(ds.getHoTen() + "");
            tv_ngaysinh.setText(ds.getNgaySinh() + "");
            Picasso.get().load(ds.getAnhDaiDien()).into(img_url);

        } else {
            thongtin = new ArrayList<>();
            dangnhap.setVisibility(View.VISIBLE);
            dangxuat.setVisibility(View.GONE);
            tv_hoten.setText("H??? t??n");
            tv_tentaikhoan.setText("T??n ????ng nh???p");
            tv_sdt.setText("0xx xxx xxxx");
            tv_ngaysinh.setText("Ng??y sinh");
            tv_diachi.setText("?????a ch??? ");
            tv_mail.setText("Gmail");
        }
    }


    private void Checkdatadangnhap() {
        if (thongtin.size() <= 0) {
            dangnhap.setVisibility(View.VISIBLE);
            dangxuat.setVisibility(View.VISIBLE);
            trangquanly.setVisibility(View.GONE);
        } else {
            dangnhap.setVisibility(View.INVISIBLE);
            dangxuat.setVisibility(View.VISIBLE);
            if (thongtin.get(0).getLoai().equals("admin")) {
                trangquanly.setVisibility(View.VISIBLE);
                trangquanly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DuphongDangNhap.class);
                        startActivity(intent);
                    }
                });
            } else {
                trangquanly.setVisibility(View.INVISIBLE);
            }
        }
    }


}