package com.example.jetpack2exercise;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;


public class AddUser extends AppCompatActivity {
    AppCompatEditText name;
    AppCompatEditText email;
    AppCompatEditText phone;
    AppCompatEditText desc;
    String userName, userEmail, userPhone, userDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        phone = findViewById(R.id.userPhone);
        desc = findViewById(R.id.userDesc);
        Button addUserBtn = findViewById(R.id.submitBtn);

        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {
        userName = name.getText().toString();
        userEmail = email.getText().toString();
        userPhone = phone.getText().toString();
        userDesc = desc.getText().toString();

        SaveUserRecord saveUserRecord = new SaveUserRecord();
        saveUserRecord.execute();
    }

    class SaveUserRecord extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            User user = new User();
            user.setmUserName(userName);
            user.setmUserEmail(userEmail);
            user.setmUserPhoneNumber(userPhone);
            user.setmUserDesc(userDesc);

            UserDatabase.getDatabase(getApplicationContext()).userDao().InsertUser(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(AddUser.this, "User Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddUser.this, MainActivity.class));
        }

    }
}


