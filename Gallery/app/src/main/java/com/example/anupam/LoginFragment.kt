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
import androidx.lifecycle.ViewModelProvider
import com.example.anupam.viewModel.FirebaseViewModel


class LoginFragment : Fragment() {

    private lateinit var mViewModel: FirebaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)

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

            if (email.isNotEmpty() &&  password.isNotEmpty()) {

                mViewModel.login(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    loginProgress.alpha = 0F
                    startActivity(Intent(activity, MainActivity::class.java))
                    }else{
                        loginProgress.alpha = 0F
                        Toast.makeText(activity, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("TAG", it.exception?.message.toString())
                    }
                }
            }else{
                loginProgress.alpha = 0F
                Toast.makeText(activity, "Invalid Credentials!", Toast.LENGTH_SHORT).show()

            }
        }

        regBtn.setOnClickListener {
            moveToSigin()
        }

        return view
    }

//    private fun isValidCredentials(email: String, password: String): Boolean {
//        val emailPattern: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
//
//        return if (email.isEmpty() || password.length < 8 ){
//            Toast.makeText(activity, "Invalid Credentials!", Toast.LENGTH_SHORT).show()
//            false
//        }else{
//            if (email.trim().toRegex().matches(emailPattern) && password.length > 8){
//                true
//            } else{
//                Toast.makeText(activity, "Invalid Credentials!", Toast.LENGTH_SHORT).show()
//                false
//            }
//        }
//    }

    private fun moveToSigin() {
        val fragmentManager: FragmentManager? = fragmentManager
        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        val signinFragment = SigninFragment()
        transaction?.replace(R.id.authContainer, signinFragment)
        transaction?.addToBackStack("signinFragment")
        transaction?.commit()
    }

}
