package com.example.anupam

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

/**
 * A simple [Fragment] subclass.
 */
class TimelineFragment : Fragment() {

    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()
    var dbRef =  db.collection("Categories").document(currentUser).collection("nature")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_timeline, container, false)
        val mTitle = arrayOf("Anupam", "Ashutosh", "Lakshya", "Subarno", "Akaansha", "Ankit", "Astha", "Bharat", "Naveen", "Priya", "Ujjwal")
       // val mImage =
        //adding data to a ArrayList.
        var mDatasetSample = ArrayList<String>()

        for (i in mTitle.indices) {
            mDatasetSample.add(mTitle[i])
        }
//            dbRef.orderBy("timestamp", Query.Direction.DESCENDING).get()
//                .addOnSuccessListener { documents ->
//                    for (document in documents){
//                        val categoryModel = document.toObject(CategoryModel::class.java)
//                        mDatasetSample.add(categoryModel)
//                        Log.d("DataSet", mDatasetSample.toString())
//                        Log.d("CatList", categoryModel.imageUrl)}
//                }



        val mRecyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView)

        mLayoutManager = LinearLayoutManager(activity)
        if (mRecyclerView != null) {
            mRecyclerView.layoutManager = mLayoutManager
        }



        mAdapter = activity?.let { MyAdapter(mDatasetSample, it) }!!
//        Log.d("DataSetSend", mDatasetSample.toString())
//        mAdapter = MyAdapter(mDatasetSample, this)
        if (mRecyclerView != null) {
            mRecyclerView.adapter = mAdapter
        }

        return view
    }

}
