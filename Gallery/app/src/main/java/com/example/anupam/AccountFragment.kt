package com.example.anupam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.anupam.ViewModel.FirebaseViewModel
import com.example.anupam.model.CategoryModel
import com.example.anupam.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


/**
 * A simple [Fragment] subclass.
 */
class AccountFragment: Fragment() {

    private lateinit var mViewModel: FirebaseViewModel

    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true).build()

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()
//    var dbRef =  db.collection("Categories").document(currentUser).collection("nature")
    var dbRef = db.collection("Users").document("Categories").collection(currentUser).document("Category8")
    var strogeReference: StorageReference = FirebaseStorage.getInstance().reference

    private lateinit var  imageView: CircleImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)

        db.firestoreSettings = settings

        dbRef.get()
            .addOnSuccessListener { documents ->
                    val categoryModel = documents.toObject(CategoryModel::class.java)
                    Picasso.get().load(categoryModel?.catImage).into(imageView)
                    Log.d("CatList", categoryModel?.catImage)
            }


        var view: View = inflater.inflate(R.layout.fragment_account, container, false)

//        val btn: AppCompatButton = view.findViewById(R.id.dataBtn)
        imageView = view.findViewById(R.id.profileImageView)
        val name: AppCompatTextView = view.findViewById(R.id.userName)
        val email: AppCompatTextView = view.findViewById(R.id.userEmail)
        val logout: AppCompatButton = view.findViewById(R.id.logoutBtn)

        var currentUser: String = mAuth.currentUser?.uid.toString()
        val documentReference = db.collection("Users").document(currentUser)
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot != null) {
                var user = documentSnapshot.toObject(UserModel::class.java)!!
                name.text = user.name
                email.text = user.email
                Log.d("TAG", user?.email + " & " + user?.name)
            }
        }

        imageView.setOnClickListener {
            selectImage()
        }

        logout.setOnClickListener {
            mViewModel.logout()
            startActivity(Intent(activity, AuthActiivity::class.java))
        }

        return view
    }

    private fun selectImage() {

    }

}
