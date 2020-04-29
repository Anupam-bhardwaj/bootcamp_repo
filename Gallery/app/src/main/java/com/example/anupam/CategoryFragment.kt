package com.example.anupam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anupam.viewModel.FirebaseViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CategoryFragment : Fragment() {

    private lateinit var mViewModel: FirebaseViewModel

    lateinit var mAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_category, container, false)
        val recylerView: RecyclerView = view.findViewById(R.id.mainCategoryList)

        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        mAdapter = CategoryAdapter(this.context!!, this)

        mViewModel.loadCategory().observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {
                mAdapter.setCategories(it)
                recylerView.adapter = mAdapter
                recylerView.layoutManager = GridLayoutManager(context, 2)
            }
        })


        var addCat: FloatingActionButton = view.findViewById(R.id.addCategory)

        addCat.setOnClickListener {

                val categoryDialog: CategoryDialogeFragment = CategoryDialogeFragment()
                fragmentManager?.let { it1 -> categoryDialog.show(it1, "CategoryDialoge") }

            }

        return view
    }

}
