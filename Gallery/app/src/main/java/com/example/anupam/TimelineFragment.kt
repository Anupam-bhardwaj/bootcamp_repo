package com.example.anupam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anupam.viewModel.FirebaseViewModel

class TimelineFragment : Fragment() {

    private lateinit var mViewModel: FirebaseViewModel

    private lateinit var timelineAdapter: TimelineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_timeline, container, false)
        val mRecyclerView: RecyclerView? = view.findViewById(R.id.recyclerView)

        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        timelineAdapter = TimelineAdapter(this.context!!, this)

        mViewModel.loadTimeline().observe(viewLifecycleOwner, Observer { timelineImages ->
            timelineImages?.let {
                timelineAdapter.setTimelineImages(it)
                mRecyclerView?.adapter = timelineAdapter
                mRecyclerView?.layoutManager = LinearLayoutManager(context)
            }
        })

        return view
    }

}
