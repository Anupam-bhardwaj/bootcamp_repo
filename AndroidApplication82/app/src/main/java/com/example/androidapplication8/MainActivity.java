package com.example.androidapplication8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MainActivity extends AppCompatActivity {

    private TextView titleText;
    private TextView dataText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.titleView);
        dataText = findViewById(R.id.dataView);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();

        titleText.setText(intent.getStringExtra("title"));
        dataText.setText(intent.getStringExtra("data"));
        Glide
                .with(getApplicationContext())
                .load(intent.getStringExtra("image"))
                .placeholder(R.drawable.ic_image)
                .fitCenter()
                .into(imageView);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                Log.d("Token", "token: "+task.getResult().getToken());
            }
        });
    }
}
