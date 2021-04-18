package com.rajendra.vacationtourapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.RecentsData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder> {

    Context context;
    List<RecentsData> recentsDataList;

    public RecentsAdapter(Context context, List<RecentsData> recentsDataList) {
        this.context = context;
        this.recentsDataList = recentsDataList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_item, parent, false);

        // here we create a recyclerview row item layout file
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {

//        holder.countryName.setText(recentsDataList.get(position).getCountryName());
//        holder.placeName.setText(recentsDataList.get(position).getPlaceName());
//        holder.price.setText(recentsDataList.get(position).getPrice());
//        holder.placeImage.setImageResource(recentsDataList.get(position).getImageUrl());
        holder.setLocationData(recentsDataList.get(position));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, DetailsActivity.class);
//
//                context.startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return recentsDataList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder {

//        ImageView placeImage;
//        TextView placeName, countryName, rating;
        ImageView imageUrl;
        TextView title, location, starRating;
        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageUrl = itemView.findViewById(R.id.url_image);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            starRating = itemView.findViewById(R.id.starRating);

        }

        void setLocationData(RecentsData travelLocation) {
            Picasso.get().load(travelLocation.getImageUrl()).into(imageUrl);
            title.setText(travelLocation.getTitle());
            location.setText(travelLocation.getLocation());
            starRating.setText(String.valueOf(travelLocation.starRating));
        }
    }
}
