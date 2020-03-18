package com.example.androidassignment9;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.androidassignment9.App.CHANNEL_ID1;
import static com.example.androidassignment9.App.CHANNEL_ID2;

public class MyReceiver extends BroadcastReceiver {

//    private static final String CHANNEL_ID1 = "Channel_1";
//    private static final String CHANNEL_ID2 = "Channel_2";
    private static final int NOTIFICATION_ID = 1;

    private final static AtomicInteger c = new AtomicInteger(0);


    NotificationManagerCompat notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context, CHANNEL_ID1);
            builder1.setSmallIcon(R.drawable.ic_launcher_foreground);
            builder1.setContentText("Notification from Channel 1");
            builder1.setContentTitle("Channel 1");
            builder1.setAutoCancel(true);
            builder1.setPriority(NotificationCompat.PRIORITY_HIGH);

            notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(getID(), builder1.build());


            Thread.sleep(60000);

            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(context, CHANNEL_ID2);
            builder2.setSmallIcon(R.drawable.ic_launcher_foreground);
            builder2.setContentText("Notification from Channel 2");
            builder2.setContentTitle("Channel 2");
            builder2.setAutoCancel(true);
            builder2.setPriority(NotificationCompat.PRIORITY_HIGH);

            notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(getID(), builder2.build());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    public static int getID() {
        return c.incrementAndGet();
    }
}
