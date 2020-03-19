package com.example.asynctaskassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 1;
    private String url = "https://wallpapercave.com/wp/nnhpAHd.jpg";
//    private String strUrl1 = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg";

    ImageView imageView;
    ProgressBar progressBar;
    TextView progressText;

    Button download;
    Button pauseBtn;
    Button resumeBtn;
    Button serviceBtn;

    InputStream inputStream;
    OutputStream outputStream;
    private boolean isPaused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){}
//            Toast.makeText(this, "You have already granted the permission", Toast.LENGTH_SHORT).show();
        else{
            requestStoragePermissions();
        }


        imageView = findViewById(R.id.image);
        download = findViewById(R.id.download);
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.textView);

        pauseBtn = findViewById(R.id.pause);
        resumeBtn = findViewById(R.id.resume);
        serviceBtn = findViewById(R.id.service);

        progressBar.setAlpha(0);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()){
                    progressBar.setAlpha(1);
                    progressText.setAlpha(1);
                    Toast.makeText(MainActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
                    new DownloadProcess().execute(url);
                }else {
                    Toast.makeText(MainActivity.this, "Internet Not connected...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        resumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resume();
            }
        });

        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setAlpha(1);
                Intent intent = new Intent(MainActivity.this,DownloadService.class);
                intent.putExtra("url", url);
                intent.putExtra("receiver", new DownloadReceiver(new Handler()));
                startService(intent);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "PERMiSSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestStoragePermissions() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE )){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }

    }

    public boolean isConnected() {
       Context context = getApplicationContext();
       ConnectivityManager connection = (ConnectivityManager)context.getSystemService(Service.CONNECTIVITY_SERVICE);
       if (connection != null){
           NetworkInfo networkInfo = connection.getActiveNetworkInfo();
           if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED){
               return true;
           }
       }
        return false;
    }

    public void pause()
    {
        this.isPaused = true;
    }

    public void resume()
    {
        this.isPaused = false;
        Toast.makeText(this, "Download Resumed...", Toast.LENGTH_SHORT).show();
    }

    private class DownloadProcess extends AsyncTask<String, Integer, String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setIndeterminate(false);
            progressBar.setMax(100);

        }

        @Override
        protected String doInBackground(String...Url) {

            int count;
            String imageURL = url;
            int imageSize;
            URL url1;
            URLConnection connection;

            try {
                url1 = new URL(Url[0]);
                connection = url1.openConnection();
                connection.connect();
                imageSize = connection.getContentLength();
                Log.d("FileSize", String.valueOf(imageSize));
                inputStream = new java.net.URL(imageURL).openStream();
                String sdcard = getExternalStorageDirectory().getPath();
                outputStream = new FileOutputStream(sdcard + "/TestImage.jpg");

                byte data[] = new byte[1024];
                int total = 0;

                while ((count = inputStream.read(data)) != -1) {
                    if (!isConnected() || isPaused){
                        uiThread();
                    }
                    while (!isConnected() || isPaused){
                       Thread.sleep(1);
                    }
                    Log.d("count", String.valueOf(count));
                    total += count;
                    Log.d("total", String.valueOf(total));
                    Thread.sleep(75);
                    Log.d("debug", String.valueOf((total*100)/imageSize));
                    publishProgress((int)(total*100)/imageSize);
                    outputStream.write(data, 0, count);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setProgress(progress[0]);
            progressText.setText(progress[0] + "/" + progressBar.getMax());

        }

        @Override
        protected void onPostExecute(String unused) {

            String GetPath = getExternalStorageDirectory().toString() + "/TestImage.jpg";


            imageView.setImageDrawable(Drawable.createFromPath(GetPath));
            progressText.setAlpha(0);
            progressBar.setAlpha(0);
            Toast.makeText(MainActivity.this, "Download Completed!", Toast.LENGTH_SHORT).show();
        }

        public void uiThread(){

            Thread toastThread = new Thread(){
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Download Paused...", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            toastThread.start();
        }

    }

    private class DownloadReceiver extends ResultReceiver {
        public DownloadReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == DownloadService.UPDATE_PROGRESS) {
                int progress = resultData.getInt("progress"); //get the progress
                progressBar.setProgress(progress);
                progressText.setText(progress + "/" + progressBar.getMax());
                if (progress == 100) {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(getExternalStorageDirectory().getAbsolutePath() + "/" + "Image1.jpg"));
                }
            }

        }
    }
}
