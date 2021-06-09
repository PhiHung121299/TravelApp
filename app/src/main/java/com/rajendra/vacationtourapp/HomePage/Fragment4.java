package com.rajendra.vacationtourapp.HomePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.rajendra.vacationtourapp.DangNhap;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.DiaDiem;
import com.rajendra.vacationtourapp.model.Nguoidung;
import com.rajendra.vacationtourapp.preferences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Fragment4 extends Fragment {
    DatabaseReference myData;
    TextView tv_diachi, tv_sdt, tv_hoten, tv_mail, tv_ngaysinh, tv_tentaikhoan;
    String trangthai = "false";
    Button dangnhap, dangxuat, trangquanly;
    public static List<Nguoidung> thongtin = new ArrayList<>();
    private static SharedPreferences luutaikhoan;
    ImageView img_url;
String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment4, container, false);
        tv_diachi = (TextView) view.findViewById(R.id.tv_diachi);
        tv_sdt = (TextView) view.findViewById(R.id.tv_sdt);
        tv_mail = (TextView) view.findViewById(R.id.tv_gmail);
        tv_hoten = (TextView) view.findViewById(R.id.tv_hotennguoidung);
        tv_ngaysinh = (TextView) view.findViewById(R.id.tv_ngaysinh);
        tv_tentaikhoan = (TextView) view.findViewById(R.id.tv_tendangnhap);
        dangnhap = (Button) view.findViewById(R.id.bt_dangnhap);
        trangquanly = (Button) view.findViewById(R.id.dentrangquanly);
        dangxuat = (Button) view.findViewById(R.id.bt_dangxuat);

        img_url = (ImageView) view.findViewById(R.id.image_avata);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DangNhap.class);
                startActivity(intent);

            }
        });
        Checkdatadangnhap();
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

                Intent intent = new Intent(getActivity(), DangNhap.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void logout() {
        SharedPreferences.Editor editor = luutaikhoan.edit();
        editor.clear();
        editor.apply();
        checkdata();
    }

    private void checkdata() {
        Nguoidung ds = (Nguoidung) getActivity().getIntent().getSerializableExtra("thongtin");
        String TenTk = ds.getHoTen()+"";
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
            tv_hoten.setText("Họ tên");
            tv_tentaikhoan.setText("Tên đăng nhập");
            tv_sdt.setText("0xx xxx xxxx");
            tv_ngaysinh.setText("Ngày sinh");
            tv_diachi.setText("Địa chỉ ");
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
                        Intent intent = new Intent(getActivity(), DangNhap.class);
                        startActivity(intent);
                    }
                });
            } else {
                trangquanly.setVisibility(View.INVISIBLE);
            }
        }
    }
}