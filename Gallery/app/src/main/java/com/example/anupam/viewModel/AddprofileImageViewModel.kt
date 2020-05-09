package com.example.anupam.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.anupam.model.Repository

class AddprofileImageViewModel(private val repository: Repository): ViewModel() {

    fun addProfileImage(profileImage: Uri){
        repository.addProfileImage(profileImage)
    }

}
