package com.rajendra.vacationtourapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.NhaNghi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AllNhaNghiAdapter extends ArrayAdapter<NhaNghi> {
    ArrayList<NhaNghi> ds = new ArrayList<NhaNghi>();
    Activity context;
    int layout;

    public AllNhaNghiAdapter(Context context, int resource, ArrayList<NhaNghi> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        ds = objects;
        layout = resource;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(layout, null);
        ImageView imageUrl = (ImageView) view.findViewById(R.id.textUrlImg);
        TextView title = (TextView) view.findViewById(R.id.textTitle);
        TextView location = (TextView) view.findViewById(R.id.textLocation);
        TextView starRating = (TextView) view.findViewById(R.id.textStarRating);
        NhaNghi s = ds.get(position);
        title.setText(s.getTen());
        location.setText(s.getDiaChi());
        starRating.setText(String.valueOf(s.getDiemDanhGia()));
        Picasso.get().load(s.getHinhAnh()).into(imageUrl);
        return view;
    }

}

