package com.example.anupam.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.anupam.model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class SigninViewModel(private val repository: Repository): ViewModel() {

    fun signin(email: String, password: String, name: String): Task<AuthResult> {
        return repository.sigin(email, password, name)
    }
}