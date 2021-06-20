package com.rajendra.vacationtourapp.Admin.admin_adapter;

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
import com.rajendra.vacationtourapp.model.NhaNghi;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class adapter_ql_nhanghi extends ArrayAdapter<NhaNghi> {
    ArrayList<NhaNghi> nn = new ArrayList<NhaNghi>();
    Activity context;
    int layout;


    public adapter_ql_nhanghi(Context context, int resource, ArrayList<NhaNghi> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        nn = objects;
        layout = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(layout, null);
        ImageView hinh = (ImageView) view.findViewById(R.id.item_hinhanh);
        TextView tv2 = (TextView) view.findViewById(R.id.item_ten);
        TextView tv3 = (TextView) view.findViewById(R.id.item_sdt);
        NhaNghi s = nn.get(position);
        tv2.setText(s.getTen());
        tv3.setText(s.getSdt());
        Picasso.get().load(s.getHinhAnh()).into(hinh);


        return view;
    }

}

