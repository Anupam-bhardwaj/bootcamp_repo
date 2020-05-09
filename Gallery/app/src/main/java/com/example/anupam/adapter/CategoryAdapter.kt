package com.example.anupam.adapter

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
import com.example.anupam.view.fragment.CategoryDetailFragment
import com.example.anupam.view.fragment.CategoryFragment
import com.example.anupam.R
import com.example.anupam.model.CategoryModel
import com.squareup.picasso.Picasso

class
CategoryAdapter(private val mContext: Context, categoryFragment: CategoryFragment): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    //categoryList Declared
    private lateinit var mCategoryDataSet: List<CategoryModel>
    private var categoryFragment: CategoryFragment = categoryFragment

    //function to add the fetched category list to the adapter categoryList
    fun setCategories(category:List<CategoryModel>){
        mCategoryDataSet = category
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //Single Item view Layout inflated
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.category_item_view,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return mCategoryDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Load category image and name from fetched Url & category Id into ImageView and TextView respectively
        Picasso.get().load(mCategoryDataSet[position].catImage).placeholder(R.color.placeholderBackgroung).into(holder.categoryImage)
        holder.categoryName.text = mCategoryDataSet[position].catId

        //single item clickListener
        holder.categoryImage.setOnClickListener {
            //goToDetails method called
            goToDetails(mCategoryDataSet[position].catId, it)
        }

    }

    //goToDetail method declared with category Name and itemView as parameters
    private fun goToDetails(catId: String?, it: View) {

        //Bundle declared & initialized to pass category name to next fragment
        var categoryId: Bundle = Bundle()
        categoryId.putString("categoryName", catId)
        Log.d("CategoryAdapter: CatID", catId)

        //fragment manager and fragment transactions initialized for CategoryDetailFragment
        val activity: AppCompatActivity = it.context as AppCompatActivity
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        val categoryDetailFragment: CategoryDetailFragment =
            CategoryDetailFragment()
        categoryDetailFragment.arguments = categoryId

        fragmentTransaction.replace(R.id.mainContainer, categoryDetailFragment)
        fragmentTransaction.addToBackStack("CategoryFragment")
        fragmentTransaction.commit()

    }

    //Custom ViewHolder to initialize the View Components
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var categoryImage: ImageView = view.findViewById(R.id.singleCategoryImage)
        var categoryName: AppCompatTextView = view.findViewById(R.id.singleCategoryName)

    }

}
