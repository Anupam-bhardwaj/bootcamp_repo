package com.example.anupam.model

import android.net.Uri

class Repository {

    private val firebaseModel: FirebaseModel = FirebaseModel()

    fun login(email: String, password: String) = firebaseModel.login(email, password)

    fun sigin(email: String, password: String, name: String) = firebaseModel.signin(email, password, name)

    fun loadCategory() = firebaseModel.loadCategory()

    fun addCategory(catImageUri: Uri, categoryName: String, categoryId: String) =
        firebaseModel.addCategory(catImageUri, categoryName, categoryId)

    fun loadCategoryDetail() = firebaseModel.loadCategoryDetail()

    fun deleteImage(documentId: String) = firebaseModel.deleteImage(documentId)

    fun logout() = firebaseModel.logout()
}