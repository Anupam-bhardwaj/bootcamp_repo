package com.example.anupam

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class FullImageViewFragment(context: Context?) : Fragment() {

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()

    var dbRef = db.collection("Users").document("timeline").collection(currentUser)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bundle: Bundle? = arguments
        val imageUrl: String? = bundle?.get("imageUrl").toString()
        val documentId: String? = bundle?.get("documentId").toString()

        Log.d("FullImageView", documentId)
        val view: View = inflater.inflate(R.layout.fragment_full_image_view, container, false)

        val imageView: AppCompatImageView = view.findViewById(R.id.fullImageView)
        val closeBtn: Button = view.findViewById(R.id.closeBtn)

        Picasso.get().load(imageUrl).into(imageView)

        closeBtn.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        return view
    }
}
