package com.example.anupam.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.anupam.model.Repository

class AddCategoryViewModel(private val repository: Repository): ViewModel() {
    fun addCategory(catImageUri: Uri, categoryName: String, categoryId: String){
        repository.addCategory(catImageUri, categoryName, categoryId)
    }
}