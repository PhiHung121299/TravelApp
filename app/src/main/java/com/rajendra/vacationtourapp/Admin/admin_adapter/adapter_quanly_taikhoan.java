package com.rajendra.vacationtourapp.Admin.admin_adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.UserOject;
import com.rajendra.vacationtourapp.model.Nguoidung;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class adapter_quanly_taikhoan extends ArrayAdapter<UserOject> {
    ArrayList<UserOject> use = new ArrayList<UserOject>();
    Activity context;
    int layout;


    public adapter_quanly_taikhoan(Context context, int resource, ArrayList<UserOject> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        use = objects;
        layout = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(layout, null);

        TextView tv1 = (TextView) view.findViewById(R.id.tv_admin_loaitk);
        TextView tv2 = (TextView) view.findViewById(R.id.tv_admin_tentaikhoan);
        TextView tv3 = (TextView) view.findViewById(R.id.tv_admin_hoten);
        TextView ngaysinh = (TextView) view.findViewById(R.id.tv_admin_ngaysinh);
        ImageView hinhanh = (ImageView) view.findViewById(R.id.img_admin_hinhanh);
        UserOject s = use.get(position);

        tv1.setText(s.getLoai());
        tv2.setText(s.getgMail());
        tv3.setText(s.getHoTen());
        ngaysinh.setText(s.getNgaySinh());
        Picasso.get().load(s.getAnhDaiDien()).into(hinhanh);

        return view;
    }

}

