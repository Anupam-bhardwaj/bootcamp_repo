package com.example.anupam.viewModel

import androidx.lifecycle.ViewModel
import com.example.anupam.model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginViewModel(private val repository: Repository): ViewModel() {
    fun login(email: String, password: String): Task<AuthResult> {
        return repository.login(email, password)
    }
}