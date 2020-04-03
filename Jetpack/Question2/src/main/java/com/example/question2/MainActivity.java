package com.example.question2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ColorViewModel viewModel;
    LinearLayout linearLayout;

    int backgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linerLayout);
        viewModel = new ViewModelProvider( this).get(ColorViewModel.class);
        linearLayout.setBackgroundColor(viewModel.getColor());
        Button button = findViewById(R.id.colorBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColor = setColour();
                viewModel.setColor(backgroundColor);
                linearLayout.setBackgroundColor(backgroundColor);
            }
        });
    }

    private int setColour() {
        Random random = new Random();
        int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        return color;
    }
}
