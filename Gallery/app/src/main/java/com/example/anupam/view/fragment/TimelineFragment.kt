package com.example.anupam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anupam.R
import com.example.anupam.adapter.TimelineAdapter
import com.example.anupam.viewModel.MyViewModelFactory
import com.example.anupam.viewModel.TimelineViewModel

class TimelineFragment : Fragment() {

    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(TimelineViewModel::class.java)

    }

    private lateinit var timelineAdapter: TimelineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_timeline, container, false)

        val placeholderText: TextView = view.findViewById(R.id.placeholderText)

        val mRecyclerView: RecyclerView? = view.findViewById(R.id.recyclerView)

        timelineAdapter = TimelineAdapter(this.context!!, this)

        mViewModel.loadTimeline().observe(viewLifecycleOwner, Observer { timelineImages ->
            timelineImages?.let {

                if (it.isNotEmpty()){
                    placeholderText.visibility = View.GONE
                }

                timelineAdapter.setTimelineImages(it)
                mRecyclerView?.adapter = timelineAdapter
                mRecyclerView?.layoutManager = LinearLayoutManager(context)
            }
        })

        return view
    }

}
