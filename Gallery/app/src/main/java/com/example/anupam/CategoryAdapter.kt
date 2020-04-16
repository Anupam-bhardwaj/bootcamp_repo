package com.example.anupam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CategoryAdapter(val categoryDataSet: ArrayList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return categoryDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Picasso.get().load(categoryDataSet[position].catImage).into(holder.categoryImage)
        holder.categoryName.text = categoryDataSet[position].catId
    }

    fun setCategoryData(categoryModel: CategoryModel) {
        categoryDataSet.add(categoryModel)
        notifyDataSetChanged()
    }

    fun removeData(categoryModel: CategoryModel) {
        categoryDataSet.remove(categoryModel)
        notifyDataSetChanged()

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var categoryImage: ImageView = view.findViewById(R.id.singleCategoryImage)
        var categoryName: AppCompatTextView = view.findViewById(R.id.singleCategoryName)
    }

}
