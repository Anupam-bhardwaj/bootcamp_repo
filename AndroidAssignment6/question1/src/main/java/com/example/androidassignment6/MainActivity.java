package com.example.androidassignment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText mMainText;
    private AppCompatButton mSubmitBtn;
    private AppCompatButton mRetrieveBtn;
    private AppCompatTextView mShowText;
    private final static String VALUE_KEY = "mainText";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainText = (AppCompatEditText)findViewById(R.id.mainText);
        mSubmitBtn = (AppCompatButton)findViewById(R.id.submitBtn);
        mRetrieveBtn = (AppCompatButton)findViewById(R.id.retriveBtn);
        mShowText = (AppCompatTextView)findViewById(R.id.showText);

        sharedPreferences = this.getSharedPreferences("mySharedPreference", MODE_PRIVATE);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveData();
            }
        });

        mRetrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    private void getData() {
        String mRetrievedValue = sharedPreferences.getString(VALUE_KEY, "");
        mShowText.setText(mRetrievedValue);
        Toast.makeText(MainActivity.this, "Data Retrieved...", Toast.LENGTH_LONG).show();

    }

    private void saveData() {
        SharedPreferences.Editor editor =sharedPreferences.edit();
        String mSubmitedText = mMainText.getText().toString();
        editor.putString(VALUE_KEY, mSubmitedText);
        editor.commit();
        Toast.makeText(MainActivity.this, "Data Saved Successfully...", Toast.LENGTH_LONG).show();
        mMainText.setText("");
    }
}
