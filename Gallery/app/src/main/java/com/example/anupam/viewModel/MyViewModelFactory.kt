package com.example.anupam.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.anupam.model.Repository

class MyViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SigninViewModel::class.java)){
            return SigninViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(AddCategoryViewModel::class.java)){
            return AddCategoryViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(AddimageViewModel::class.java)){
            return AddimageViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(CategoryDetailViewModel::class.java)){
            return CategoryDetailViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(TimelineViewModel::class.java)){
            return TimelineViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(AccountViewModel::class.java)){
            return AccountViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(AddprofileImageViewModel::class.java)){
            return AddprofileImageViewModel(Repository()) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}