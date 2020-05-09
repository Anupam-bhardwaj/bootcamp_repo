package com.example.anupam.model

import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.anupam.view.activity.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage

class FirebaseModel {

    private var categoryImage: Uri? = null
    private var profileImageUri: Uri? = null
    private var uploadImageUri: Uri? = null
    private var imageUrl: String? = ""
    private var profileImageUrl: String? = ""

    //FirebaseAuth
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()

    //FirebaseFireStore Setting
    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true).build()

    //FirebaserFirestore
    lateinit var categoryListener: ListenerRegistration
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var userReference = db.collection("Users").document(currentUser)
    var categoryReference = db.collection("Users").document("Categories").collection(currentUser)
    var categoryDetailReference = db.collection("Users").document("Timeline").collection(currentUser)
//    val imageReference = db.collection("Users").document("Timeline").collection(currentUser)


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

    fun signin(
        email: String,
        password: String,
        name: String
    ): Task<AuthResult>{
         val  auth = mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                var currentUid: String = mAuth.currentUser?.uid.toString()
                addUserToDatabase(name, email, currentUid)
        }
        return auth
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

    fun addNewImage(categoryName: String, imageUri: Uri, imageName: String){
        val storageReference = FirebaseStorage.getInstance().reference
            .child("timeline").child(currentUser).child(imageName)
        var uploadTask = imageUri?.let { it -> storageReference.putFile(it) }
        val urlTask =  uploadTask?.continueWithTask { task ->
            if (!task.isSuccessful){
                task.exception?.let {
                    throw it
                }
            }
            storageReference.downloadUrl
        }?.addOnCompleteListener { task ->
            if (task.isSuccessful){
                uploadImageUri = task.result
                imageUrl = uploadImageUri.toString()
                addImageToFirebase(categoryName, imageUrl!!)
            }else
                Toast.makeText(MainActivity(), task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun addImageToFirebase(categoryName: String, imageUrl: String) {
        val timeStamp = hashMapOf<String, Any>(
            "timestamp" to FieldValue.serverTimestamp()
        )
        var documentId = getRandomString(20)
        val imageModel = ImageModel(documentId, categoryName, imageUrl)
        categoryDetailReference.document(documentId).set(imageModel).addOnSuccessListener {
            categoryDetailReference.document(documentId).update(timeStamp)
        }

    }

    fun loadCategoryDetail(): CollectionReference{
        db.firestoreSettings = settings
        return categoryDetailReference
    }

    fun loadTimeline(): CollectionReference{
        db.firestoreSettings = settings
        return categoryDetailReference
    }

    fun deleteImage(documentId: String){
        FirebaseFirestore.getInstance().collection("Users").document("Timeline")
            .collection(currentUser).document(documentId).delete()
    }

    fun loadUserData(): Task<DocumentSnapshot>{
        val documentSnapshot = userReference.get()
        return documentSnapshot
    }

    fun addProfileImage(profileImage: Uri){
        val profileImageName = "$currentUser+.jpg"
        val storageReference = FirebaseStorage.getInstance().reference
            .child("profileImage").child(profileImageName)
        var uploadTask = profileImage?.let { it ->
            storageReference.putFile(it)
        }
        val urlTask =  uploadTask?.continueWithTask { task ->
            if (!task.isSuccessful){
                task.exception?.let {
                    throw it
                }
            }
            storageReference.downloadUrl
        }?.addOnCompleteListener { task ->
            if (task.isSuccessful){
                profileImageUri = task.result!!
                profileImageUrl = profileImageUri.toString()
                addProfileImageToFirebase(profileImageUrl!!)
            }
        }
    }

    private fun addProfileImageToFirebase(profileImageUrl: String) {
        val profileImageMap = hashMapOf<String, Any>(
            "profileImage" to profileImageUrl
        )
        userReference.update(profileImageMap)

    }

    private fun addCategoryToFirestore(categoryId: String, categoryName: String, categoryImagePath: String) {
        val timeStamp = hashMapOf<String, Any>(
            "timestamp" to FieldValue.serverTimestamp()
        )
        var addCategoryModel = CategoryModel(categoryName, categoryImagePath)
        categoryReference.document(categoryId).set(addCategoryModel).addOnSuccessListener {
            categoryReference.document(categoryId).update(timeStamp).addOnSuccessListener {
                Log.d("AddCategory", timeStamp.toString())
            }
        }
    }

    private fun addUserToDatabase(name: String, email: String, currentUid: String) {
        db.firestoreSettings = settings
        user = UserModel(name, email, "https://firebasestorage.googleapis.com/v0/b/galleryapp-a0f9e.appspot.com/o/CategoryImage%2FIzRRneuifuVoqwq1nZzD7A5kObc2%2Fphotography.jpg?alt=media&token=88f5be0e-5efb-4186-9607-85314d787bfb")
        db.collection("Users").document(currentUid).set(user)
          
    }

    fun logout(){
        mAuth.currentUser
        mAuth.signOut()
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}
