package com.example.jetpackexercise;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpackexercise.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    ArrayList<DataModel> dataModelList = new ArrayList<>();


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,
                container, false);
        populateData();
        MyAdapter myAdapter = new MyAdapter(dataModelList, this.getContext());
        binding.setMyAdapter(myAdapter);
        return binding.getRoot();
    }

    private void populateData() {

        dataModelList.add(new DataModel("Anupam", "anupam@gmail.com", "1234567890", "Trainee"));
        dataModelList.add(new DataModel("Ashutosh", "ashutosh@gmail.com", "1234567890", "Trainee"));
        dataModelList.add(new DataModel("Lakshya", "lakshya@gmail.com", "1234567890", "Trainee"));
        dataModelList.add(new DataModel("Subarno", "subarno@gmail.com", "1234567890", "Trainee"));

    }


}
