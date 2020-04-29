package com.example.anupam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.anupam.model.TimelineModel
import com.squareup.picasso.Picasso

class TimelineAdapter(private val mContext: Context, timelineFragment: TimelineFragment) : RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {

   private lateinit var mTimelineDataSet: List<TimelineModel>
    private var timelineFragment: TimelineFragment

    init {
        this.timelineFragment = timelineFragment
    }

    fun setTimelineImages(timeline: List<TimelineModel>){
        mTimelineDataSet = timeline
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.timeline_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return mTimelineDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(mTimelineDataSet[position].imageUrl).placeholder(R.color.placeholderBackgroung).into(holder.imageView)

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imageView: AppCompatImageView = view.findViewById(R.id.timelineImage)
    }

}
