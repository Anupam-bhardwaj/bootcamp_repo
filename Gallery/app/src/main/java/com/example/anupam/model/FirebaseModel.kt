package com.example.anupam.model

import android.net.Uri
import android.widget.Toast
import com.example.anupam.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage

class FirebaseModel {

    private var categoryImage: Uri? = null
    private var profileImageUrl: String = ""

    //FirebaseAuth
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()

    //FirebaseFireStore Setting
    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true).build()

    //FirebaserFirestore
    lateinit var categoryListener: ListenerRegistration
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var categoryReference = db.collection("Users").document("Categories").collection(currentUser)
    var categoryDetailReference = db.collection("Users").document("Timeline").collection(currentUser)

    //FirebaseStorage
    private var storageReference = FirebaseStorage.getInstance().reference

    //DataSet
    val mCategoryDataSet = ArrayList<CategoryModel>()

    //ModleClasses
    lateinit var user: UserModel

    fun login(email: String, password: String): Task<AuthResult>
    {
        val auth = mAuth.signInWithEmailAndPassword(email, password)
        return auth
    }

    fun signin(email: String, password: String, name: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                var currentUid: String = mAuth.currentUser?.uid.toString()
                addToDatabase(name, email, currentUid)
        }
    }

    fun loadCategory(): CollectionReference{
        db.firestoreSettings = settings
        return categoryReference
    }


    fun addCategory(catImageUri: Uri, categoryName: String, categoryId: String){
        val storageReference = FirebaseStorage.getInstance().reference
            .child(currentUser).child("$categoryName.jpg")
        var uploadTask = catImageUri?.let { it -> storageReference.putFile(it) }
        val urlTask = uploadTask?.continueWithTask { task ->
            if (!task.isSuccessful){
                task.exception?.let {
                    throw it
                }
            }
            storageReference.downloadUrl
        }?.addOnCompleteListener { task ->
            if (task.isSuccessful){
                categoryImage = task.result
                addCategoryToFirestore(categoryId, categoryName, categoryImage.toString())

            }else
                Toast.makeText(MainActivity(), task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    fun loadCategoryDetail(): CollectionReference{
        db.firestoreSettings = settings
        return categoryDetailReference
    }

    fun deleteImage(documentId: String){
        FirebaseFirestore.getInstance().collection("Users").document("Timeline")
            .collection(currentUser).document(documentId).delete()

    }

    private fun addCategoryToFirestore(categoryId: String, categoryName: String, categoryImagePath: String) {
        var timeStamp = FieldValue.serverTimestamp()
        var addCategoryModel = AddCategoryModel(categoryName, categoryImagePath, timeStamp)
        categoryReference.document(categoryId).set(addCategoryModel).addOnSuccessListener {
            Toast.makeText(MainActivity(), "Category Added!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addToDatabase(name: String, email: String, currentUid: String) {
        db.firestoreSettings = settings
        user = UserModel(name, email)
        db.collection("Users").document(currentUid).set(user)
    }

    fun logout(){
        mAuth.currentUser
        mAuth.signOut()
    }

}
