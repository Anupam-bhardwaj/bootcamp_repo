package com.example.question1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Fragment1", "onCreateView Method");
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment1", "onCreate Method");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment1", "onActivityCreated Method");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("Fragment1", "onAttach Method");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment1", "onStart Method");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment1", "onResume Method");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment1", "onPause Method");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment1", "onStop Method");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment1", "onDestroyView Method");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment1", "onDestroy Method");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment1", "onDetach Method");
    }
}
