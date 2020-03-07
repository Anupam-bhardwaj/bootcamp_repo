package com.example.question2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText mMainText;
    private AppCompatButton mSubmitBtn;
    private AppCompatButton mRetrieveBtn;
    private AppCompatTextView mShowText;
    private final static String VALUE_KEY = "mainText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainText = (AppCompatEditText)findViewById(R.id.mainText);
        mSubmitBtn = (AppCompatButton)findViewById(R.id.submitBtn);
        mRetrieveBtn = (AppCompatButton)findViewById(R.id.retriveBtn);
        mShowText = (AppCompatTextView)findViewById(R.id.showText);



        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData();
            }
        });

        mRetrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

    }

    private void writeData() {
        String mWrittenText = mMainText.getText().toString();
        String mFinalText = mWrittenText + " ";
        try {

            FileOutputStream fileOutputStream = openFileOutput("question2.txt", MODE_APPEND);
            fileOutputStream.write(mFinalText.getBytes());
            fileOutputStream.close();
            Toast.makeText(MainActivity.this, "Data Saved Successfully...", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() {
        int end;
        String mReadedText = "";
        try {

            FileInputStream fileInputStream = openFileInput("question2.txt");
            while ((end = fileInputStream.read())!= -1){

                mReadedText = mReadedText + Character.toString((char) end);
            }
            mMainText.setText("");
            mShowText.setText(mReadedText);
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
