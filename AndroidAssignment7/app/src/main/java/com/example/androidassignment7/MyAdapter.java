package com.example.androidassignment7;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

class MyAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<MyAdapter.ViewHolder> {
       Context context;
       List<Employee> modelList;

       MainActivity obj = new MainActivity();

    public MyAdapter(Context context, List<Employee> modelList) {
        this.context = context;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(modelList.get(position).getmEmployeeName());
        holder.address.setText(modelList.get(position).getmEnployeeAddress());
        holder.phoneNumber.setText(modelList.get(position).getmPhoneNumber());
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context, holder.menuBtn);
                popupMenu.inflate(R.menu.option_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.update:

                                Intent intent = new Intent(context, InsertData.class);
                                intent.putExtra("record", modelList.get(position));
                                context.startActivity(intent);
                                break;

                            case R.id.delete:
                                deleteRecord(modelList.get(position), position);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void deleteRecord(final Employee employee, final int position) {

        class DeleteRecordTask extends AsyncTask<Void, Void, View>{

            @Override
            protected View doInBackground(Void... voids) {

                EmployeeDatabase.getDatabase(context).employeeDao().deleteEmp(employee);
                return null;
            }

            @Override
            protected void onPostExecute(View view) {
                super.onPostExecute(view);
                try {
                    modelList.remove(position);
                    notifyItemChanged(position);
                    Toast.makeText(context, "Data Deleted....", Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }


            }
        }

        DeleteRecordTask deleteRecordTask = new DeleteRecordTask();
        deleteRecordTask.execute();

    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView name;
        public AppCompatTextView address;
        public AppCompatTextView phoneNumber;
        public AppCompatTextView menuBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (AppCompatTextView)itemView.findViewById(R.id.empName);
            address = (AppCompatTextView)itemView.findViewById(R.id.address);
            phoneNumber = (AppCompatTextView)itemView.findViewById(R.id.phone);
            menuBtn = (AppCompatTextView)itemView.findViewById(R.id.textViewOptions);

        }
    }
}
