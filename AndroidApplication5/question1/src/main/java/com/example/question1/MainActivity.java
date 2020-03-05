package com.example.question1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button frag1;
    private Button frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate Method");

        frag1 = (Button)findViewById(R.id.frag1);
        frag2 = (Button)findViewById(R.id.frag2);

        frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment1 fragment = new Fragment1();
                fragmentTransaction.replace(R.id.frameContainer, fragment, "myFragment1");
                fragmentTransaction.addToBackStack("fragment1");
                fragmentTransaction.commit();

            }
        });

        frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment2 fragment2 = new Fragment2();
                fragmentTransaction.replace(R.id.frameContainer, fragment2, "myFragment2");
                fragmentTransaction.addToBackStack("fragment2");
                fragmentTransaction.commit();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart Method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume Method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause Method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop Method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy Method");
    }
}
