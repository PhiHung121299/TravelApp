package com.rajendra.vacationtourapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class adapter_Image extends ArrayAdapter<String> {
    ArrayList<String> dmsp = new ArrayList<>();
    Activity context;
    int layout;


    public adapter_Image(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        dmsp = objects;
        layout = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(layout, null);
        ImageView img = (ImageView) view.findViewById(R.id.itemImage);
        String dm = dmsp.get(position);
        Picasso.get().load(dm).into(img);


        return view;
    }
}
