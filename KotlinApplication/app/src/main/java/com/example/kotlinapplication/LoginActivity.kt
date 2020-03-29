package com.example.kotlinapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class LoginActivity : AppCompatActivity() {

    lateinit var email: AppCompatEditText
    lateinit var user: AppCompatEditText
    lateinit var pass: AppCompatEditText
    lateinit var phone: AppCompatEditText
    lateinit var signin: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById<View>(R.id.signupEmail) as AppCompatEditText
        user = findViewById<View>(R.id.signupUser) as AppCompatEditText
        pass = findViewById<View>(R.id.siginPass) as AppCompatEditText
        phone = findViewById<View>(R.id.siginPhone) as AppCompatEditText
        signin = findViewById<View>(R.id.signupBtn) as AppCompatButton

        signin.setOnClickListener {
            val mEmail = email.text.toString()
            val mUserName = user.text.toString()
            val mPhoneNumber = phone.text.toString()
            validateEmailAndPass(mEmail, mUserName, mPhoneNumber)
        }

    }

    private fun validateEmailAndPass(mEmail: String, mUserName: String, mPhoneNumber: String) {

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (mEmail.isEmpty() || pass.text.toString().length < 8) {
            Toast.makeText(applicationContext, "Invalid Entry", Toast.LENGTH_SHORT).show()
        } else {
            if (emailPattern.toRegex().matches(mEmail.trim()) && pass.text.toString().length > 8) {
                sendToMain(mEmail, mUserName, mPhoneNumber)
            } else {
                Toast.makeText(applicationContext, "Invalid Entry", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun sendToMain(mEmail: String, mUserName: String, mPhoneNumber: String) {

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", mUserName)
        intent.putExtra("email", mEmail)
        intent.putExtra("phone", mPhoneNumber)
        startActivity(intent)
        finish()

    }

}

