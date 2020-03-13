package com.example.androidapplication8;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class FirebaseNotificationService extends FirebaseMessagingService {

    private static final String TAG = "Msg";
    private static final String CHANNEL_ID2 = "CHANNEL_1";

    private NotificationCompat.Builder builder;

    int REQUEST_CODE = 0;

    public FirebaseNotificationService() {

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size()>0){

            String title, data, imgUrl;

            title = remoteMessage.getData().get("title");
            data = remoteMessage.getData().get("data");
            imgUrl = remoteMessage.getData().get("url");

            Log.d("title", title);
            Log.d("data", data);
            Log.d("url", imgUrl);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel  = new NotificationChannel(CHANNEL_ID2, "Default channel"
                        , NotificationManager.IMPORTANCE_HIGH);
                channel.enableVibration(true);
                //channel.setDescription("FCM Notification");
                notificationManager.createNotificationChannel(channel);
            }

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("title", title);
            intent.putExtra("data", data);
            intent.putExtra("image", imgUrl);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder = new NotificationCompat.Builder(this, CHANNEL_ID2);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.drawable.ic_notification);
            builder.setVibrate(new long[]{100, 200});


            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(getBitmapFromURL(imgUrl)));
            notificationManager.notify(REQUEST_CODE, builder.build());

        }
    }

    private Bitmap getBitmapFromURL(String imgUrl) {
        try {
            Bitmap bitmap = Glide.with(this).asBitmap().load(imgUrl).into(100, 100).get();
            return bitmap;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onNewToken(String token) {
        Log.d("Token", token);
    }


}

