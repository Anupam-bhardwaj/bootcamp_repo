package com.example.anupam.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anupam.model.ImageModel
import com.example.anupam.model.Repository
import com.example.anupam.model.TimelineModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class TimelineViewModel(private val repository: Repository): ViewModel() {
    private val TAG = "TimelineViewModel"

    private var timelineImages: MutableLiveData<List<TimelineModel>> = MutableLiveData()

    fun loadTimeline(): LiveData<List<TimelineModel>> {
        repository.loadTimeline().orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(
            EventListener<QuerySnapshot>{ value, e ->
                if (e != null){
                    Log.w(TAG, "Listen Fail", e)
                    timelineImages.value = null
                    return@EventListener
                }
                var timelineList: MutableList<TimelineModel> = mutableListOf()
                for (doc in value!!){
                    var timelineImageItem = doc.toObject(TimelineModel::class.java)
                    timelineList.add(timelineImageItem)
                }
                timelineImages.value = timelineList
            })
        return timelineImages
    }
}