package com.example.anupam.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.anupam.view.activity.MainActivity
import com.example.anupam.R
import com.example.anupam.viewModel.AddCategoryViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class AddCategoryDialogeFragment : DialogFragment() {

    //ViewModel Initialized as lazy
    private val mViewModel by lazy {
        ViewModelProvider(this).get(AddCategoryViewModel::class.java)

    }

    //Firebase variables declared
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: String = mAuth.currentUser?.uid.toString()

    var dbRef = FirebaseFirestore.getInstance().collection("Users").document("Categories").collection(currentUser)

    var documentSize: Int? = 0
    lateinit var imagePath: String
    private var catImageUri: Uri? = null
    private var catImage: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //get category count
        var categoryCounterTask: Task<QuerySnapshot> = dbRef.get().addOnSuccessListener {
            documentSize = it.size()
        }

        //Layout Inflated
        var rootView: View = inflater.inflate(R.layout.fragment_category_dialoge, container, true)

        var catName: EditText = rootView.findViewById(R.id.categoryName)
        var addImage: AppCompatButton = rootView.findViewById(R.id.addCatImageBtn)
        var addBtn: Button = rootView.findViewById(R.id.addCategoryDialogBtn)
        var cancelBtn: Button = rootView.findViewById(R.id.cancelCategoryDialogBtn)


        //addImage Btn to select image for the category cover from gallery
        addImage.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        //cancel dialog btn
        cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }

        //addBtn to add category to firebase
        addBtn.setOnClickListener {

            var categoryName:String = catName.text.toString()
            if (categoryName.isEmpty()) {
                catName.error = "Please Enter Category Name"
            } else if (catImageUri == null){
                Toast.makeText(MainActivity(), "Please select a category image", Toast.LENGTH_SHORT).show()
            }else{
                dialog?.dismiss()
                mViewModel.addCategory(catImageUri!!, categoryName, getCategoryId())
                imagePath = catImage?.path.toString()

            }
        }

        return rootView
    }

    //method to get unique category reference for firebase
    private fun getCategoryId(): String {

        documentSize = documentSize?.plus(1)
        var categoryId: String = "Category$documentSize"
        Log.d("CategoryId", categoryId)

        return categoryId
    }

    //onActivityResult method override to get Image Uri for the selected category cover image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        catImageUri = data?.data!!
        Log.d("ImagePicker", catImageUri.toString())
    }

    override fun onStart() {
        super.onStart()

        var dialog = dialog
        if (dialog!=null){
            val width: Int = ViewGroup.LayoutParams.MATCH_PARENT
            val height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width,height)

        }
    }
}


