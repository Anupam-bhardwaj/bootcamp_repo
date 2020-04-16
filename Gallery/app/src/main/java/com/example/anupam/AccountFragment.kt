package com.example.anupam

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


/**
 * A simple [Fragment] subclass.
 */
class AccountFragment: Fragment() {

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()
//    var dbRef =  db.collection("Categories").document(currentUser).collection("nature")
    var dbRef = db.collection("Users").document("Categories").collection(currentUser).document("Category8")
    var strogeReference: StorageReference = FirebaseStorage.getInstance().reference

    var userName: String? = null
    var userEmail: String? = null

    private var mainImageUri: Uri? = null
    var downloadUri: Uri? = null
    var imageId: String? = null
    lateinit var  imageView: CircleImageView

    lateinit var userModel: UserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dbRef.get()
            .addOnSuccessListener { documents ->
                    val categoryModel = documents.toObject(CategoryModel::class.java)
                    Picasso.get().load(categoryModel?.catImage).into(imageView)
                    Log.d("CatList", categoryModel?.catImage)}


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
                userName = user.name
                userEmail = user.email
                name.text = userName
                email.text = userEmail
//                setName(user?.name.toString())
//                setEmail(user?.email.toString())
                Log.d("TAG", user?.email + " & " + user?.name)
            }
        }



        imageView.setOnClickListener {
            selectImage()
        }


        logout.setOnClickListener {

            mAuth.currentUser
            mAuth.signOut()
            startActivity(Intent(activity, AuthActiivity::class.java))
        }

        return view
    }

    private fun selectImage() {

    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.capture_image_menu, menu)
//    }


}
