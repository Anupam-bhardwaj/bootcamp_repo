package com.example.anupam

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anupam.model.TimelineModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

/**
 * A simple [Fragment] subclass.
 */
class TimelineFragment : Fragment() {

    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .build()

    lateinit var timelineListener: ListenerRegistration

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()
    var dbRef =  db.collection("Users").document("Timeline").collection(currentUser)

    val mTimelineDataset = ArrayList<TimelineModel>()
    private lateinit var timelineAdapter: TimelineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        db.firestoreSettings = settings

        val view: View = inflater.inflate(R.layout.fragment_timeline, container, false)

        val mRecyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView)

        val mLayoutManager = LinearLayoutManager(context)
        mRecyclerView?.layoutManager = mLayoutManager

        timelineAdapter = TimelineAdapter(mTimelineDataset)

        loadData()

        mRecyclerView?.adapter = timelineAdapter

        if (mTimelineDataset.isNotEmpty()){
            timelineListener.remove()
        }

        return view
    }

    private fun loadData() {
        timelineListener = dbRef.orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("TAG", "listen:error", e)
                return@addSnapshotListener
            }
            for (dc in snapshot!!.documentChanges) {
                val timelineModel = dc.document.toObject(TimelineModel::class.java)

                when (dc.type) {
                    DocumentChange.Type.ADDED -> {
                        timelineAdapter.setCategoryData(timelineModel)
                        Log.d("AccountFragment", "Added image: ${timelineModel.imageUrl}") }
                    //                DocumentChange.Type.MODIFIED -> {mAdapter.setCategoryData(categoryModel)
                    //                    Log.d("AccountFragment", "Modified image: ${categoryModel.catImage}")}
                    DocumentChange.Type.REMOVED -> {timelineAdapter.removeData(timelineModel)
                        Log.d("AccountFragment", "Removed image: $timelineModel")}
                }
            }
        }
    }

}
