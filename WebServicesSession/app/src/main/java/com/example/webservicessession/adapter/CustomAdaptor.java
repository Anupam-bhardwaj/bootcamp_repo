package com.example.webservicessession.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.webservicessession.Model.RetroData;
import com.example.webservicessession.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.CustomViewHolder> {

    private List<RetroData> dataList;
    private Context context;

    public CustomAdaptor(List<RetroData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        RetroData retroData = dataList.get(position);
        holder.name.setText(retroData.getName());
        holder.msg.setText(retroData.getMessage());
        String imageUrl = retroData.getProfileImage().replace("http", "https");
        Picasso.get().load(imageUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataList.size();

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public final View mView;

        TextView name;
        TextView msg;
        private ImageView imageView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            name = mView.findViewById(R.id.name);
            msg = mView.findViewById(R.id.message);
            imageView = mView.findViewById(R.id.image);

        }
    }
}
