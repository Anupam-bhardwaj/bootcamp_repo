package com.example.anupam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.logging.Logger

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mAuth = FirebaseAuth.getInstance()
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment
        val loginProgress: ProgressBar = view.findViewById(R.id.loginProgress)
        var emailTextView: AppCompatEditText = view.findViewById(R.id.emailEditText)
        var passwordTextView: AppCompatEditText = view.findViewById(R.id.passwordEditText)
        var loginBtn: AppCompatButton = view.findViewById(R.id.loginBtn)
        var regBtn: AppCompatTextView = view.findViewById(R.id.regBtn)
        loginProgress.alpha = 0F

        loginBtn.setOnClickListener {
            loginProgress.alpha = 1F
            var email: String = emailTextView.text.toString()
            var password: String = passwordTextView.text.toString()

            Log.d("TAG", email + " & " +password)
            login(email, password)

        }

        regBtn.setOnClickListener {

            var fragmentManager: FragmentManager? = fragmentManager
            val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
            val signinFragment = SigninFragment()
            transaction?.replace(R.id.authContainer, signinFragment)
            transaction?.commit()

        }

        return view
    }

    private fun login(email: String, password: String) {

        activity?.let {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        loginProgress.alpha = 0F
                        startActivity(Intent(activity, MainActivity::class.java))

                    } else {
                        loginProgress.alpha = 0F
                        Toast.makeText(activity, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("TAG", task.exception?.message.toString())

                    }
                }
        }

    }

}
