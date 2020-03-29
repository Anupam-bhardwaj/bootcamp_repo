package com.example.kotlinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {

    lateinit var mUserName: AppCompatTextView
    lateinit var mEmail: AppCompatTextView
    lateinit var mPhone: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserName = findViewById(R.id.mainUser) as AppCompatTextView
        mEmail = findViewById(R.id.mainEmail) as AppCompatTextView
        mPhone = findViewById(R.id.mainPhone) as AppCompatTextView

        val intent = intent

        var userName: String = intent.getStringExtra("user")
        var email: String = intent.getStringExtra("email")
        var phone: String = intent.getStringExtra("phone")

        mUserName.text = userName
        mEmail.text = email
        mPhone.text = phone

    }
}
