package com.example.anupam

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.anupam.viewModel.FirebaseViewModel
import com.example.anupam.model.ImageModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class CategoryDetailAdapter(private val mContext: Context, categoryDetailFragment: CategoryDetailFragment): RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {

    private lateinit var mViewModel: FirebaseViewModel

    private lateinit var mCategoryImageDataSet: List<ImageModel>
    private var categoryDetailFragment:CategoryDetailFragment

    fun setImageData(images: List<ImageModel>) {
        mCategoryImageDataSet = images
        notifyDataSetChanged()

    }

    init{
        this.categoryDetailFragment = categoryDetailFragment
    }

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()


    var position: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return mCategoryImageDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(mCategoryImageDataSet[position].imageUrl).placeholder(R.color.placeholderBackgroung)
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            getFullScreen(mCategoryImageDataSet[position].imageUrl, mCategoryImageDataSet[position].documentId, it)
        }

        holder.menuBtn.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(it.context, holder.menuBtn)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->

                when(item.itemId){
                R.id.deleteBtn-> {
                    var mDocumentId = mCategoryImageDataSet[position].documentId.toString()
                    mViewModel = ViewModelProvider(categoryDetailFragment).get(FirebaseViewModel::class.java)
                    mViewModel.deleteImage(mDocumentId)
                    Toast.makeText(it.context, "Image Deleted!", Toast.LENGTH_SHORT).show()
                }
                    else -> Toast.makeText(it.context, "Something Went Wrong.", Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
    }

    private fun getFullScreen(imageUrl: String?, documentId: String?, it: View) {
        var image: Bundle = Bundle()
        image.putString("imageUrl", imageUrl)
        image.putString("documentId", documentId)

        val activity: AppCompatActivity = it.context as AppCompatActivity
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        val fullImage: FullImageViewFragment = FullImageViewFragment(activity)
        fullImage.arguments = image
        fragmentTransaction.replace(R.id.mainContainer, fullImage)
        fragmentTransaction.addToBackStack("FullImageViewFragment")
        fragmentTransaction.commit()

    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.singleImageView)
        var menuBtn: AppCompatTextView = view.findViewById(R.id.menuBtn)
    }

}
