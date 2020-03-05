package com.example.question2;

import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Button frag1;
    private Button frag2;
    private Button showBtn;
    private Button hideBtn;
    private Button remBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate Method");

        frag1 = (Button)findViewById(R.id.frag1);
        frag2 = (Button)findViewById(R.id.frag2);
        showBtn = (Button)findViewById(R.id.showBtn);
        hideBtn = (Button)findViewById(R.id.hideBtn);
        remBtn = (Button)findViewById(R.id.remBtn);

        final FragmentManager fragmentManager = getSupportFragmentManager();
//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final Fragment1 fragment1 = new Fragment1();
        final Fragment2 fragment2 = new Fragment2();

        frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Fragment1 fragment1 = new Fragment1();
                fragmentTransaction.replace(R.id.frameContainer, fragment1, "myFragment1");
                fragmentTransaction.addToBackStack("fragment1");
                fragmentTransaction.commit();

            }
        });

        frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Fragment2 fragment2 = new Fragment2();
                fragmentTransaction.replace(R.id.frameContainer, fragment2, "myFragment2");
                fragmentTransaction.addToBackStack("fragment2");
                fragmentTransaction.commit();

            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Fragment2 fragment2 = new Fragment2();
                fragmentTransaction.show(fragment2);
                fragmentTransaction.commit();

            }
        });

        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Fragment2 fragment2 = new Fragment2();
                fragmentTransaction.hide(fragment2);
                fragmentTransaction.commit();
            }
        });

        remBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment2);
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
