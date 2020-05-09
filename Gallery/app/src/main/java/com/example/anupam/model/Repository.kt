package com.example.anupam.model

import android.net.Uri
import androidx.fragment.app.FragmentActivity

class Repository {

    private val firebaseModel: FirebaseModel = FirebaseModel()

    fun login(email: String, password: String) = firebaseModel.login(email, password)

    fun sigin(
        email: String,
        password: String,
        name: String

    ) = firebaseModel.signin(email, password, name)

    fun loadCategory() = firebaseModel.loadCategory()

    fun addCategory(catImageUri: Uri, categoryName: String, categoryId: String) =
        firebaseModel.addCategory(catImageUri, categoryName, categoryId)

    fun addNewImage(categoryName: String, imageUri: Uri, imageName: String) = firebaseModel.addNewImage(categoryName, imageUri, imageName)

    fun loadUserData() = firebaseModel.loadUserData()

    fun addProfileImage(profileImage: Uri) = firebaseModel.addProfileImage(profileImage)

    fun loadCategoryDetail() = firebaseModel.loadCategoryDetail()

    fun loadTimeline() = firebaseModel.loadTimeline()

    fun deleteImage(documentId: String) = firebaseModel.deleteImage(documentId)

    fun logout() = firebaseModel.logout()
}