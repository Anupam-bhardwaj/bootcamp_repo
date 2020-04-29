package com.example.anupam

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
import com.example.anupam.viewModel.FirebaseViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage

class CategoryDialogeFragment : DialogFragment() {

    private lateinit var mViewModel: FirebaseViewModel

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var storageReference = FirebaseStorage.getInstance().reference
    private var currentUser: String = mAuth.currentUser?.uid.toString()
    private var catImageUri: Uri? = null
    private var catImage: Uri? = null

    var documentSize: Int? = 0
    lateinit var imagePath: String

    var dbRef = FirebaseFirestore.getInstance().collection("Users").document("Categories").collection(currentUser)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var categoryCounterTask: Task<QuerySnapshot> = dbRef.get().addOnSuccessListener {
            documentSize = it.size()
            val catIdNumber = documentSize?.plus(1)
            val categoryId: String = "Category$catIdNumber"

            Log.d("DocumentSize", documentSize.toString())
            Log.d("categoryId", categoryId)
        }

        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)

        var rootView: View = inflater.inflate(R.layout.fragment_category_dialoge, container, true)

        var catName: EditText = rootView.findViewById(R.id.categoryName)
        var addImage: AppCompatButton = rootView.findViewById(R.id.addCatImageBtn)
        var addBtn: Button = rootView.findViewById(R.id.addCategoryDialogBtn)
        var cancelBtn: Button = rootView.findViewById(R.id.cancelCategoryDialogBtn)


        addImage.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }

        addBtn.setOnClickListener {

            var categoryName:String = catName.text.toString()
            if (categoryName.isEmpty()) {
                catName.error = "Please Enter Category Name"
            } else if (catImageUri == null){
                Toast.makeText(MainActivity(), "Please select a category image", Toast.LENGTH_SHORT).show()
            }else{
                dialog?.dismiss()
                mViewModel.addCategory(catImageUri!!, categoryName, getCategoryId(categoryName))
//                Toast.makeText(MainActivity(), "Category Added!", Toast.LENGTH_SHORT).show()
                imagePath = catImage?.path.toString()

            }
        }

        return rootView
    }

    private fun getCategoryId(categoryName: String): String {

        categoryName.toLowerCase()
        Log.d("CategoryDialog", categoryName)
        documentSize = documentSize?.plus(1)
        var categoryId: String = "Category$documentSize"
        Log.d("CategoryId", categoryId)

        return categoryId
    }

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


