package com.example.anupam

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.anupam.model.CategoryModel
import com.squareup.picasso.Picasso

class CategoryAdapter(private val mContext: Context, categoryFragment: CategoryFragment): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var mCategoryDataSet: List<CategoryModel>
    private var categoryFragment:CategoryFragment

    fun setCategories(category:List<CategoryModel>){
        mCategoryDataSet = category
        notifyDataSetChanged()
    }
    init{
        this.categoryFragment = categoryFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return mCategoryDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(mCategoryDataSet[position].catImage).placeholder(R.color.placeholderBackgroung).into(holder.categoryImage)
        holder.categoryName.text = mCategoryDataSet[position].catId
        holder.categoryImage.setOnClickListener {
            goToDetails(mCategoryDataSet[position].catId, it)
        }

    }

    private fun goToDetails(catId: String?, it: View) {

        var categoryId: Bundle = Bundle()

        categoryId.putString("categoryName", catId)
        Log.d("CategoryAdapter: CatID", catId)

        val activity: AppCompatActivity = it.context as AppCompatActivity
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        val categoryDetailFragment: CategoryDetailFragment = CategoryDetailFragment()
        categoryDetailFragment.arguments = categoryId

        fragmentTransaction.replace(R.id.mainContainer, categoryDetailFragment)
        fragmentTransaction.addToBackStack("CategoryFragment")
        fragmentTransaction.commit()

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var categoryImage: ImageView = view.findViewById(R.id.singleCategoryImage)
        var categoryName: AppCompatTextView = view.findViewById(R.id.singleCategoryName)

    }

}
