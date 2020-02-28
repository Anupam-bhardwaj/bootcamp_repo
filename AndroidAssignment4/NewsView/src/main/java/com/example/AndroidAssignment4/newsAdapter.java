package com.example.AndroidAssignment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class newsAdapter extends RecyclerView.Adapter {

    public ArrayList<Model> dataSet;
    Context mContext;
    int total_types;

    //Constructor of newsAdaptor Class.
    public newsAdapter(ArrayList<Model>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        // Creating Multiple Views for two different type of News Types
        switch (viewType){
            case Model.BANNER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, parent, false);
                return new bannerItemView(view);
            case Model.SMALL_BANNER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent, false);
                return new smallBannerItemView(view);
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {

        //Getting News Type from the Model Class.
        switch (dataSet.get(position).type){
            case 0:
                return Model.BANNER_TYPE;
            case 1:
                return Model.SMALL_BANNER_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //Binding Data to the appropriate Views using switch case.
        Model model = dataSet.get(position);
        if (model!=null){
            switch (model.type){
                case Model.BANNER_TYPE:
                    ((bannerItemView) holder).bannerImage.setImageResource(model.getImage());
                    ((bannerItemView) holder).bannerTitle.setText(model.title);
                    ((bannerItemView) holder).bannerDesc.setText(model.desc);

                    break;
                case Model.SMALL_BANNER_TYPE:
                    ((smallBannerItemView) holder).smallBannerImage.setImageResource(model.getImage());
                    ((smallBannerItemView) holder).smallBannerTitle.setText(model.title);
                    ((smallBannerItemView) holder).smallBannerDesc.setText(model.desc);
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    //ViewHolder for the Banner Image type News Item
    class bannerItemView extends RecyclerView.ViewHolder{
        ImageView bannerImage;
        TextView bannerTitle;
        TextView bannerDesc;

        public bannerItemView(@NonNull View itemView) {
            super(itemView);
            bannerImage = (ImageView)itemView.findViewById(R.id.bannerImage);
            bannerTitle = (TextView)itemView.findViewById(R.id.bannerTitle);
            bannerDesc = (TextView)itemView.findViewById(R.id.bannerDesc);
        }
    }

    //View Holder for the Small image type News Item
    class smallBannerItemView extends RecyclerView.ViewHolder{

        ImageView smallBannerImage;
        TextView smallBannerTitle;
        TextView smallBannerDesc;

        public smallBannerItemView(@NonNull View itemView) {
            super(itemView);

            smallBannerImage = (ImageView)itemView.findViewById(R.id.smallBannerImage);
            smallBannerTitle = (TextView)itemView.findViewById(R.id.smallBannerTitle);
            smallBannerDesc = (TextView)itemView.findViewById(R.id.smallBannerDesc);
        }
    }
}
