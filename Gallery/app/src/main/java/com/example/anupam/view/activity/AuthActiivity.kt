package com.example.anupam.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.anupam.view.fragment.LoginFragment
import com.example.anupam.R

class AuthActiivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_actiivity)

    }

    override fun onStart() {
        super.onStart()

        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        var loginFragment = LoginFragment()
        fragmentTransaction.replace(R.id.authContainer, loginFragment)
        fragmentTransaction.commit()

    }
}
