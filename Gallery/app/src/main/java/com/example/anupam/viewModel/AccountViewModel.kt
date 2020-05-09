package com.example.anupam.viewModel

import androidx.lifecycle.ViewModel
import com.example.anupam.model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

class AccountViewModel(private val repository: Repository): ViewModel() {

    fun loadUserData(): Task<DocumentSnapshot> {
        return repository.loadUserData()
    }

    fun logout(){
        repository.logout()
    }
}