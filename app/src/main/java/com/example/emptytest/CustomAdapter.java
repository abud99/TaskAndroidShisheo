package com.example.emptytest;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<Restaurant> list;
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        CustomViewHolder custom = new CustomViewHolder(view);
        return custom;
    }

    public CustomAdapter(ArrayList<Restaurant> list){
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            Restaurant item = this.list.get(position);
            holder.name.setText(item.getName());
            holder.description.setText(item.getDescrp());
            holder.offer.setText(item.getOffer());
            Glide.with(holder.urlImage.getContext()).load(item.getUrl()).into(holder.urlImage);
            holder.urlImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.stars.setVisibility(View.VISIBLE);
                }
            });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        public ImageButton urlImage;
        public TextView name;
        public TextView description;
        public TextView offer;
        public RatingBar stars;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            urlImage = itemView.findViewById(R.id.urlImage);
            name = itemView.findViewById(R.id.nameAPI);
            description = itemView.findViewById(R.id.descriptionAPI);
            offer = itemView.findViewById(R.id.offerAPI);
            stars = itemView.findViewById(R.id.starAPI);
        }
    }


}
