package com.example.anupam.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.anupam.R
import com.example.anupam.adapter.CategoryAdapter
import com.example.anupam.viewModel.CategoryViewModel
import com.example.anupam.viewModel.MyViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CategoryFragment : Fragment() {

    //ViewModel Declared
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(CategoryViewModel::class.java)

    }

    //Adapter Declared
    lateinit var mAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //View Inflated
        val view: View = inflater.inflate(R.layout.fragment_category, container, false)

        val placeholderText: TextView = view.findViewById(R.id.placeholderText)

        //recyclerView Initialized
        val recylerView: RecyclerView = view.findViewById(R.id.mainCategoryList)


        //Custom Adapter Initialized
        mAdapter = CategoryAdapter(this.context!!, this)

        //ViewModel function to load categories called
        mViewModel.loadCategory().observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {

                if (it.isNotEmpty()){
                    placeholderText.visibility = View.GONE
                }
                //fetched categories passed to the adapter through Adapter's setCategories method
                mAdapter.setCategories(it)

                //custom adapter assigned to recyclerView
                recylerView.adapter = mAdapter

                //layout manager assigned to recyclerView
                recylerView.layoutManager = GridLayoutManager(context, 2)
            }
        })


        //addCategory Button initialized
        var addCat: FloatingActionButton = view.findViewById(R.id.addCategory)

        //addCategory Button clickListener
        addCat.setOnClickListener {

            //AddCategoryDialog Fragment Called
            val addCategoryDialog: AddCategoryDialogeFragment =
                AddCategoryDialogeFragment()
            fragmentManager?.let { it1 -> addCategoryDialog.show(it1, "AddCategoryDialoge") }

            }

        return view
    }

}
