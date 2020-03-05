
package com.example.question2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Fragment2", "onCreateView Method");
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment2", "onCreate Method");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment2", "onActivityCreated Method");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("Fragment2", "onAttach Method");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment2", "onStart Method");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment2", "onResume Method");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment2", "onPause Method");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment2", "onStop Method");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment2", "onDestroyView Method");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment2", "onDestroy Method");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment2", "onDetach Method");
    }
}
