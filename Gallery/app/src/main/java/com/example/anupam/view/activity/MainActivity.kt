package com.example.anupam.view.activity

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.anupam.R
import com.example.anupam.utils.isConnected
import com.example.anupam.utils.permissionCheck
import com.example.anupam.view.fragment.TimelineFragment
import com.example.anupam.view.fragment.AccountFragment
import com.example.anupam.view.fragment.CategoryFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var FRAGMENT_TAG: String

    lateinit var mAuth: FirebaseAuth

    internal var selecterdFragment: Fragment? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            TimelineFragment(),
            selecterdFragment.toString()
        ).commit()

        chipNavigationBar.setOnItemSelectedListener{ id ->

            when(id){
                R.id.home -> {
                    selecterdFragment =
                        TimelineFragment()
                    FRAGMENT_TAG = "TimelineFragment"

                }

                R.id.category -> {
                    selecterdFragment =
                        CategoryFragment()
                    FRAGMENT_TAG = "CategoryFragment"
                }

                R.id.account -> {
                    selecterdFragment =
                        AccountFragment()
                    FRAGMENT_TAG = "AccountFragment"
                }
            }

            if ( selecterdFragment!= null){
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    selecterdFragment!!
                ).addToBackStack(FRAGMENT_TAG).commit()
            }
        }
    }


    override fun onStart() {
        super.onStart()

        this.permissionCheck()

        if (!this.isConnected()){
            Toast.makeText(this, "No internet Connection, Please turn on the Internet!", Toast.LENGTH_SHORT).show()
        }else {

        var currentUser: FirebaseUser? = mAuth.currentUser
        if (currentUser == null){
            startActivity(Intent(this, AuthActiivity::class.java))
        }}
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted!!", Toast.LENGTH_SHORT).show()
        }
    }
}
