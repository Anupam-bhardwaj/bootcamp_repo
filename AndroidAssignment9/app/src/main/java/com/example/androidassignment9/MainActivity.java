package com.example.androidassignment9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private Button startBtn;
    private Button cancelBtn;

    private NotificationCompat.Builder notificationBuilder1;
    private NotificationCompat.Builder notificationBuilder2;


    AlarmManager alarmManager;
//    AlarmManager alarmManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.submit);
        cancelBtn = findViewById(R.id.cancel);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                final PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, getRequestCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);


                long triggerTime = System.currentTimeMillis();
                long intervalTime = 20000*6;

                Log.d("msg", String.valueOf(triggerTime));
                Log.d("msg2", String.valueOf(intervalTime));

                Toast.makeText(MainActivity.this, "Reminder Set", Toast.LENGTH_SHORT).show();
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerTime,  intervalTime,pendingIntent);



            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancelAlarm();

            }
        });

    }

    private int getRequestCode() {

        Random random = new Random();
        return random.nextInt(1000);

    }

    private void cancelAlarm() {

        Intent intent = new Intent();
        intent.setAction("com.example.androidassignment9.receiver.Message");
        intent.addCategory("android.intent.category.DEFAULT");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        alarmManager.cancel(pendingIntent);
    }
}