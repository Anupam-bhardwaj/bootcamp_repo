package com.example.jetpackexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpackexercise.databinding.ItemViewBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<DataModel> dataModelList;
    private Context ctx;

    public MyAdapter(ArrayList<DataModel> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        ctx = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemViewBinding itemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_view, parent, false);

        return new ViewHolder(itemViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel dataModel = dataModelList.get(position);
        holder.bind(dataModel);

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemViewBinding itemViewBinding;
        public ViewHolder(@NonNull ItemViewBinding itemView) {
            super(itemView.getRoot());
            this.itemViewBinding = itemView;
        }

        public void bind(DataModel dataModel) {
            itemViewBinding.setVariable(BR.model, dataModel);
            itemViewBinding.executePendingBindings();
        }
    }
}
