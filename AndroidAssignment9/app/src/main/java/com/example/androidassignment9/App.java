package com.example.androidassignment9;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;

public class App extends Application {

    public static final String CHANNEL_ID1 = "Channel_1";
    public static final String CHANNEL_ID2 = "Channel_2";
    @Override
    public void onCreate() {
        super.onCreate();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        NotificationChannel notificationChannel1 = new NotificationChannel(CHANNEL_ID1, "Channel1"
                , NotificationManager.IMPORTANCE_HIGH);
        notificationChannel1.enableVibration(true);
        notificationChannel1.setLightColor(Color.RED);
        notificationChannel1.enableVibration(true);
        notificationChannel1.setDescription("Basic Notification 1");

        NotificationManager manager1 = getSystemService(NotificationManager.class);
        manager1.createNotificationChannel(notificationChannel1);

        NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID2, "Channel2"
                , NotificationManager.IMPORTANCE_HIGH);
        notificationChannel2.enableVibration(true);
        notificationChannel2.setLightColor(Color.RED);
        notificationChannel2.enableVibration(true);
        notificationChannel2.setDescription("Basic Notification 2");

        NotificationManager manager2 = getSystemService(NotificationManager.class);
        manager2.createNotificationChannel(notificationChannel2);
        }

    }
}
