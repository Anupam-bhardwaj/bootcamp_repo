package com.example.anupam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    internal var selecterdFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.mainContainer, TimelineFragment(),
            selecterdFragment.toString()
        ).commit()

        chipNavigationBar.setOnItemSelectedListener{ id ->

            when(id){
                R.id.home -> {
                    selecterdFragment = TimelineFragment()

                }

                R.id.category -> {
                    selecterdFragment = CategoryFragment()
                }

                R.id.account -> {
                    selecterdFragment = AccountFragment()
                }
            }

            if ( selecterdFragment!= null){
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer,
                    selecterdFragment!!
                ).commit()
            }
        }


//        var logoutBtn: AppCompatButton = findViewById(R.id.logoutBtn)
//        logoutBtn.setOnClickListener {
//            mAuth.signOut()
//            startActivity(Intent(this, AuthActiivity::class.java))
//        }
    }


    override fun onStart() {
        super.onStart()

        var currentUser: FirebaseUser? = mAuth.currentUser
        if (currentUser == null){
            startActivity(Intent(this, AuthActiivity::class.java))
        }
    }
}
