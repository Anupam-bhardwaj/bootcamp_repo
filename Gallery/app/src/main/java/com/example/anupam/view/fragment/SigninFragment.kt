package com.example.anupam.view.fragment

import android.content.Intent
import android.os.Bundle
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
import com.example.anupam.R
import com.example.anupam.view.activity.MainActivity
import com.example.anupam.viewModel.MyViewModelFactory
import com.example.anupam.viewModel.SigninViewModel


class SigninFragment : Fragment() {

    //ViewModel Initialized as lazy
    private val mViewModel by lazy {
       ViewModelProvider(this, MyViewModelFactory()).get(SigninViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //View Inflated
        val view: View = inflater.inflate(R.layout.fragment_signin, container, false)

        //view components initialized
        val loginProgress: ProgressBar = view.findViewById(R.id.loginProgress)

        val nameTextView: AppCompatEditText = view.findViewById(R.id.nameEditText)
        val emailTextView: AppCompatEditText = view.findViewById(R.id.emailEditText)
        val passwordTextView: AppCompatEditText = view.findViewById(R.id.passwordEditText)

        val signinBtn: AppCompatButton = view.findViewById(R.id.signinBtn)
        val loginBtn: AppCompatTextView = view.findViewById(R.id.loginBtn)

        loginProgress.alpha = 0F

        //signinBtn clickListener
        signinBtn.setOnClickListener {
            loginProgress.alpha = 1F
            var name: String = nameTextView.text.toString()
            var email: String = emailTextView.text.toString()
            var password: String = passwordTextView.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
                mViewModel.signin(email, password, name).addOnSuccessListener {
                    loginProgress.alpha = 0F
                    startActivity(Intent(activity, MainActivity::class.java))
                    Toast.makeText(activity, "Signin Successful!", Toast.LENGTH_SHORT).show()
                }

            }else{
                loginProgress.alpha = 0F
                Toast.makeText(activity, "Invalid Credentials!", Toast.LENGTH_SHORT).show()

            }
        }

        //loginBtn clickListener
        loginBtn.setOnClickListener {
            moveToLogin()
        }

        return view
    }

    //function to move to login fragment
    private fun moveToLogin() {
        val fragmentManager: FragmentManager? = fragmentManager
        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        val loginFragment = LoginFragment()
        transaction?.replace(R.id.authContainer, loginFragment)
        transaction?.commit()
    }

}
