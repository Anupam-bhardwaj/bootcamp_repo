package com.example.anupam.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anupam.model.CategoryModel
import com.example.anupam.model.Repository
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class CategoryViewModel(private val repository: Repository): ViewModel() {

    private val TAG = "Category ViewModel"

    private var savedCategories: MutableLiveData<List<CategoryModel>> = MutableLiveData()

    fun loadCategory(): LiveData<List<CategoryModel>> {
        repository.loadCategory().addSnapshotListener(EventListener<QuerySnapshot>{ value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                savedCategories.value = null
                return@EventListener
            }
            var savedCategoryList: MutableList<CategoryModel> = mutableListOf()
            for (doc in value!!){
                var categoryItem = doc.toObject(CategoryModel::class.java)
                savedCategoryList.add(categoryItem)

            }
            savedCategories.value = savedCategoryList

        })

        return savedCategories
    }

}