package com.example.jetpack2exercise;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpack2exercise.databinding.ItemViewBinding;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<User> mUser;

    public MyAdapter() {}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewBinding itemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_view, parent, false);
        return new ViewHolder(itemViewBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mUser != null){
            User current = mUser.get(position);
            holder.bind(current);
        }
    }

    @Override
    public int getItemCount() {
        if (mUser != null){
            return mUser.size();
        }else {
            return 0;
        }
    }

    void setmUser(List<User> user){
        mUser = user;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ItemViewBinding itemViewBinding;

        public ViewHolder(ItemViewBinding itemView) {
            super(itemView.getRoot());
            this.itemViewBinding = itemView;
        }

        public void bind(User user){
            itemViewBinding.setVariable(BR.model, user);
        }
    }
}
