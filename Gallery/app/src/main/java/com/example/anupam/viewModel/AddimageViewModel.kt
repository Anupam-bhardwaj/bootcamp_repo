package com.example.anupam.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.anupam.model.Repository

class AddimageViewModel(private val repository: Repository): ViewModel() {

    fun addNewImage(categoryName: String, imageUri: Uri, imageName: String){
        repository.addNewImage(categoryName, imageUri, imageName)
    }

}