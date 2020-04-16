package com.example.anupam

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

//@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CategoryFragment : Fragment() {

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()
    var dbRef = db.collection("Users").document("Categories").collection(currentUser)

    val mCategoryDataSet = ArrayList<CategoryModel>()
    lateinit var mAdapter: CategoryAdapter


//    public var cateName: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var imageCounter: Int = 0

//        getCategoryData()

        val view: View = inflater.inflate(R.layout.fragment_category, container, false)
        val recylerView: RecyclerView = view.findViewById(R.id.mainCategoryList)

        val mLayoutManger = GridLayoutManager(context, 2)
        recylerView.layoutManager = mLayoutManger

    dbRef.addSnapshotListener { snapshot, e ->
        if (e != null) {
            Log.w("TAG", "listen:error", e)
            return@addSnapshotListener
        }
        for (dc in snapshot!!.documentChanges) {
            val categoryModel = dc.document.toObject(CategoryModel::class.java)

            when (dc.type) {
                DocumentChange.Type.ADDED -> {
                    mAdapter.setCategoryData(categoryModel)
                    Log.d("AccountFragment", "Added image: ${categoryModel.catImage}")}
//                DocumentChange.Type.MODIFIED -> {mAdapter.setCategoryData(categoryModel)
//                    Log.d("AccountFragment", "Modified image: ${categoryModel.catImage}")}
                DocumentChange.Type.REMOVED -> {mAdapter.removeData(categoryModel)
                Log.d("AccountFragment", "Removed image: ${categoryModel}")}
            }
        }
    }

         mAdapter = CategoryAdapter(mCategoryDataSet)

        recylerView.adapter = mAdapter

        var addCat: FloatingActionButton = view.findViewById(R.id.addCategory)

        addCat.setOnClickListener {

                val categoryDialog: CategoryDialogeFragment = CategoryDialogeFragment()
                fragmentManager?.let { it1 -> categoryDialog.show(it1, "CategoryDialoge") }

            }

        return view
    }

}
