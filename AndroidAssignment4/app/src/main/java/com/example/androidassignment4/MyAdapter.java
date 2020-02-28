package com.example.androidassignment4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> data;

    //Constructor of the Adaptor Class.
    public MyAdapter(ArrayList<String> data){
        this.data = data;
    }

    //onCreateViewHolder, onBindViewHolder, getItemCount methods Implemented
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //inflating the single item view layout.
        View nameText = inflater.inflate(R.layout.question1_layout, parent, false);
        return new MyViewHolder(nameText);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //binding data to the layout.
        holder.nameText.setText(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    //Custom View Holder Class to hold the Elements in the single item layout file
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nameText = itemView.findViewById(R.id.nameView);
        }
    }

}
