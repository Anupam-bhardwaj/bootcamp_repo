package com.example.androidassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText email;
    EditText user;
    EditText pass;
    EditText phone;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText)findViewById(R.id.signupEmail);
        user = (EditText)findViewById(R.id.signupUser);
        pass = (EditText)findViewById(R.id.siginPass);
        phone = (EditText)findViewById(R.id.siginPhone);
        signin = (Button)findViewById(R.id.signupBtn);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString();
                String UserName = user.getText().toString();
                String phoneNumber = phone.getText().toString();

                validateEmailAndPass(Email, UserName, phoneNumber);

                }


        });

    }

    private void validateEmailAndPass(String Email, String UserName, String phoneNumber) {

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(email.getText().toString().isEmpty() || pass.getText().toString().length()<8) {
            Toast.makeText(getApplicationContext(),"Invalid Entry",Toast.LENGTH_SHORT).show();
        }else {
            if (email.getText().toString().trim().matches(emailPattern) && pass.getText().toString().length() > 8) {

               sendToMain();


            } else {
                Toast.makeText(getApplicationContext(), "Invalid Entry", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void sendToMain() {
        String Email = email.getText().toString();
        String UserName = user.getText().toString();
        String phoneNumber = phone.getText().toString();

        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        intent.putExtra("user", UserName);
        intent.putExtra("email", Email);
        intent.putExtra("phone", phoneNumber);
        startActivity(intent);
    }
}
