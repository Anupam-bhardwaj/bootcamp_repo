package com.example.androidassignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView username;
    TextView phone;
    TextView email;
    EditText url;
    Button openUrl;
    Button permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (TextView)findViewById(R.id.mainUser);
        email = (TextView)findViewById(R.id.mainEmail);
        phone = (TextView)findViewById(R.id.mainPhone);
        url = (EditText)findViewById(R.id.mainUrl);
        openUrl = (Button) findViewById(R.id.mainUrlBtn);
        permission = (Button)findViewById(R.id.permissionCheck);

        Intent intent =getIntent();

        String userName = intent.getStringExtra("user");
        String EmailText = intent.getStringExtra("email");
        String phoneNumber = intent.getStringExtra("phone");


        username.setText(userName);
        email.setText(EmailText);
        phone.setText(phoneNumber);


        openUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = validateUrl();

                Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(urlIntent);
            }
        });

        permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionCheck();

            }
        });


    }

    private String validateUrl() {

        String Url = url.getText().toString();

        if(Url.indexOf("https://")<0){

            Url = "https://" + Url;
        }
        return Url;

    }

    private void permissionCheck() {

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CAMERA)) {

                Toast.makeText(MainActivity.this, "Permission Not Granted", Toast.LENGTH_LONG).show();

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        1);
            } else {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        1);

                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Permission has already been granted", Toast.LENGTH_LONG).show();
        }

    }
}
