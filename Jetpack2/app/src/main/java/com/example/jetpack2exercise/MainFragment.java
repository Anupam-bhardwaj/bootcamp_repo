package com.example.jetpack2exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpack2exercise.databinding.FragmentMainBinding;

import java.util.List;

public class MainFragment extends Fragment {

    private UserViewModel userViewModel;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final MyAdapter myAdapter = new MyAdapter();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getmAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                myAdapter.setmUser(users);
            }
        });
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main, container, false);

        binding.setMyAdapter(myAdapter);
        return binding.getRoot();
    }
}
