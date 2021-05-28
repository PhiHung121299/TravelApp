package com.rajendra.vacationtourapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.DiaDiem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSeach extends FirebaseRecyclerAdapter<DiaDiem, AdapterSeach.myviewholder> {


    public AdapterSeach(FirebaseRecyclerOptions<DiaDiem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final DiaDiem model) {

        holder.title.setText(model.getTitle());
        holder.loaction.setText(model.getLocation());
        holder.ratting.setText(model.getStarRating() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Toast.makeText(activity, model.getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(activity, DetailsActivity.class);
                i.putExtra("dsdd", model);
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