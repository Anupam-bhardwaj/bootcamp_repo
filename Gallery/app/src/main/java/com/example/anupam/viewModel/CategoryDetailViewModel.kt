package com.example.anupam.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anupam.model.ImageModel
import com.example.anupam.model.Repository
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class CategoryDetailViewModel(private val repository: Repository): ViewModel() {

    private val TAG = "CategoryDetailViewmodel"


    private var categoryImages: MutableLiveData<List<ImageModel>> = MutableLiveData()

    fun loadCategoryDetail(categoryName: String): LiveData<List<ImageModel>> {
        repository.loadCategoryDetail().whereEqualTo("categoryName", categoryName).addSnapshotListener(
            EventListener<QuerySnapshot>{ value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen Fail", e)
                    categoryImages.value = null
                    return@EventListener
                }
                var categoryImageList: MutableList<ImageModel> = mutableListOf()
                for (doc in value!!){
                    var categoryImageItem = doc.toObject(ImageModel::class.java)
                    categoryImageList.add(categoryImageItem)
                }
                categoryImages.value = categoryImageList
            })
        return categoryImages
    }

    fun deleteImage(documentId: String){
        repository.deleteImage(documentId)
    }

}