package com.example.anupam

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.fragment_login.*


class SigninFragment : Fragment() {

    lateinit var mAuth: FirebaseAuth
    lateinit var db:FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAuth = FirebaseAuth.getInstance()

        val view: View = inflater.inflate(R.layout.fragment_signin, container, false)

        val loginProgress: ProgressBar = view.findViewById(R.id.loginProgress)
        var nameTextView: AppCompatEditText = view.findViewById(R.id.nameEditText)
        var emailTextView: AppCompatEditText = view.findViewById(R.id.emailEditText)
        var passwordTextView: AppCompatEditText = view.findViewById(R.id.passwordEditText)
        var signinBtn: AppCompatButton = view.findViewById(R.id.signinBtn)
        var loginBtn: AppCompatTextView = view.findViewById(R.id.loginBtn)
        loginProgress.alpha = 0F

        signinBtn.setOnClickListener {
            loginProgress.alpha = 1F
            var name: String = nameTextView.text.toString()
            var email: String = emailTextView.text.toString()
            var password: String = passwordTextView.text.toString()

            signin(name, email, password)

        }

        return view
    }

    private fun signin(name: String, email: String, password: String) {

        activity?.let {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        var currentUid: String = mAuth.currentUser?.uid.toString()
                        addToDatabase(name, email, currentUid)

                     } else {
                        Toast.makeText(activity, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

    private fun addToDatabase(name: String, email: String, currentUid: String) {

        db = FirebaseFirestore.getInstance()

        var user = UserModel(name, email)

        activity?.let {
            db.collection("Users").document(currentUid)
                .set(user)
                .addOnSuccessListener {
                    loginProgress.alpha = 0F
                    startActivity(Intent(activity, MainActivity::class.java))
                    Toast.makeText(
                        activity,
                        "Signin Successfull!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener(it) { exception ->
                    Toast.makeText(
                        activity,
                        exception.message.toString(), Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }

}
