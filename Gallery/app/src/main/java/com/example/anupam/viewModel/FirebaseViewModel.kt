package com.example.anupam.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anupam.model.CategoryModel
import com.example.anupam.model.ImageModel
import com.example.anupam.model.Repository
import com.example.anupam.model.TimelineModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class FirebaseViewModel(): ViewModel() {
    private val TAG = "FIRESTORE_VIEW_MODEL"

    private val repository: Repository = Repository()

    private var savedCategories: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    private var categoryImages: MutableLiveData<List<ImageModel>> = MutableLiveData()
    private var timelineImages: MutableLiveData<List<TimelineModel>> = MutableLiveData()

    fun login(email: String, password: String): Task<AuthResult>{
        return repository.login(email, password)
    }

    fun signin(email: String, password: String, name: String){
        repository.sigin(email, password, name)
    }

    fun loadCategory(): LiveData<List<CategoryModel>>{
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

    fun addCategory(catImageUri: Uri, categoryName: String, categoryId: String){
        repository.addCategory(catImageUri, categoryName, categoryId)
    }

    fun addNewImage(categoryName: String, imageUri: Uri, imageName: String){
        repository.addNewImage(categoryName, imageUri, imageName)
    }

    fun loadCategoryDetail(categoryName: String): LiveData<List<ImageModel>>{
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

    fun loadTimeline(): LiveData<List<TimelineModel>>{
        repository.loadTimeline().orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(
            EventListener<QuerySnapshot>{ value, e ->
                if (e != null){
                    Log.w(TAG, "Listen Fail", e)
                    categoryImages.value = null
                    return@EventListener
                }
                var timelineList: MutableList<TimelineModel> = mutableListOf()
                for (doc in value!!){
                    var timelineImageItem = doc.toObject(TimelineModel::class.java)
                    timelineList.add(timelineImageItem)
                }
                timelineImages.value = timelineList
            })
        return timelineImages
    }

    fun deleteImage(documentId: String){
        repository.deleteImage(documentId)
    }

    fun loadUserData(): Task<DocumentSnapshot>{
        return repository.loadUserData()
    }

    fun addProfileImage(profileImage: Uri){
        repository.addProfileImage(profileImage)
    }

    fun logout(){
        repository.logout()
    }

}