package com.rajendra.vacationtourapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.rajendra.vacationtourapp.ChitietNhaNghi;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.DiaDiem;
import com.rajendra.vacationtourapp.model.NhaNghi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSeachNhaNghi extends FirebaseRecyclerAdapter<NhaNghi, AdapterSeachNhaNghi.myviewholder> {


    public AdapterSeachNhaNghi(FirebaseRecyclerOptions<NhaNghi> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final NhaNghi model) {

        holder.title.setText(model.getTen());
        holder.loaction.setText(model.diaChi);
        holder.ratting.setText(model.getDiemDanhGia() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Toast.makeText(activity, model.ten, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(activity, ChitietNhaNghi.class);
                i.putExtra("dsnn", model);
                activity.startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder {

        TextView title, loaction, ratting;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.nametext);
            loaction = (TextView) itemView.findViewById(R.id.coursetext);
            ratting = (TextView) itemView.findViewById(R.id.emailtext);
        }
    }
}