package com.rajendra.vacationtourapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.DiaDiem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopDiaDiemAdapter extends RecyclerView.Adapter<TopDiaDiemAdapter.TopPlacesViewHolder> {
    private static final String TAG = "MyActivity";
    Context context;
    ArrayList<DiaDiem> diaDiemList;

    public TopDiaDiemAdapter(Context context, ArrayList<DiaDiem> diaDiemList) {
        this.context = context;
        this.diaDiemList = diaDiemList;
    }

    @NonNull
    @Override
    public TopPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_item, parent, false);

        // here we create a recyclerview row item layout file
        return new TopPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopPlacesViewHolder holder, final int position) {

        holder.setLocationData(diaDiemList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("dsdd", diaDiemList.get(position));
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return diaDiemList.size();
    }

    public static final class TopPlacesViewHolder extends RecyclerView.ViewHolder {

        //        public String title,location,;
//        public Float starRating;
        ImageView imageUrl;
        TextView title, location, starRating;

        public TopPlacesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageUrl = itemView.findViewById(R.id.url_image);
            title = itemView.findViewById(R.id.tv_title);
            location = itemView.findViewById(R.id.location);
            starRating = itemView.findViewById(R.id.starRating);

        }

        void setLocationData(DiaDiem travelLocation) {
            Picasso.get().load(travelLocation.getImageUrl()).into(imageUrl);
            title.setText(travelLocation.getTitle());
            location.setText(travelLocation.getLocation());
            starRating.setText(String.valueOf(travelLocation.starRating));
        }

    }

}
