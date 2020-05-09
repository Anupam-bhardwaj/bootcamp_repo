package com.example.anupam.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anupam.R
import com.example.anupam.adapter.CategoryDetailAdapter
import com.example.anupam.viewModel.CategoryDetailViewModel
import com.example.anupam.viewModel.MyViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CategoryDetailFragment : Fragment() {

    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(CategoryDetailViewModel::class.java)

    }

    lateinit var mAdapter: CategoryDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val positionBundel: Bundle? = arguments
        var categoryName: String = positionBundel?.get("categoryName") as String
        val title = categoryName.toUpperCase()

        val view: View = inflater.inflate(R.layout.fragment_category_detail, container, false)

        val toolBar: Toolbar = view.findViewById(R.id.categoryDetailToolbar)
        toolBar.title = title

        val recyclerView: RecyclerView = view.findViewById(R.id.categoryDetailList)

        mAdapter = CategoryDetailAdapter(
            this.context!!,
            this
        )
        mViewModel.loadCategoryDetail(categoryName).observe(viewLifecycleOwner, Observer { categoryImages ->
            categoryImages?.let {
                mAdapter.setImageData(it)
                recyclerView.adapter = mAdapter
                recyclerView.layoutManager = GridLayoutManager(context, 2)
//                recyclerView.layoutManager = StaggeredGridLayoutManager(2, 1)
            }
        })

        val addImageBtn: FloatingActionButton = view.findViewById(R.id.addImageBtn)

        addImageBtn.setOnClickListener {
            addImage(categoryName)
        }

        return view
    }

    private fun addImage(categoryName: String) {
        var categoryId: Bundle = Bundle()
        categoryId.putString("categoryName", categoryName)
        val addImageDialogeFragment: AddImageDialogeFragment =
            AddImageDialogeFragment()
        addImageDialogeFragment.arguments = categoryId
        fragmentManager?.let { it-> addImageDialogeFragment.show(it, "AddImageDialog") }
    }

}
