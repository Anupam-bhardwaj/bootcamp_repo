package com.example.anupam

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_category_dialoge.*
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class CategoryDialogeFragment : DialogFragment() {

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var storageReference = FirebaseStorage.getInstance().reference
    var currentUser: String = mAuth.currentUser?.uid.toString()
    var catImageUri: Uri? = null
    var catImage: Uri? = null

    var documentSize: Int? = 0
    lateinit var imagePath: String
//    var dbRef = db.collection("Categories").document(currentUser).collection("category")
    var dbRef = db.collection("Users").document("Categories").collection(currentUser)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var categoryCounterTask: Task<QuerySnapshot> = dbRef.get().addOnSuccessListener {
            documentSize = it.size()
            var catIdNumber = documentSize?.plus(1)
            var categoryId: String = "Category$catIdNumber"

            Log.d("DocumentSize", documentSize.toString())
            Log.d("categoryId", categoryId)
        }

//        var categoryCounter: Int = 0

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
            if (!categoryName.isEmpty()) {


                Log.d("CategoryDialog", categoryName)
                documentSize = documentSize?.plus(1)
                var categoryId: String = "Category$documentSize"
                Log.d("ImageId", categoryId)
                dialog?.dismiss()
                uploadCategoryImage(catImageUri, categoryName, categoryId)
//                uploadCategoryImage(catImageUri, categoryName, )
                imagePath = catImage?.path.toString()

            } else {
                Toast.makeText(activity, "Enter Category Name", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    private fun addCategory(categoryId: String, categoryName: String, ImagePath: String?) {
        var categoryModel = CategoryModel(categoryName, ImagePath)
        Log.d("addCategory", ImagePath)
        Log.d("addCategory", "CATEGORYID: $categoryId")
        activity.let {
            //                db.collection("Users").document("Categories").collection(currentUser)
            dbRef.document(categoryId)
                .set(categoryModel).addOnSuccessListener {
                    val timeStamp = hashMapOf<String, Any>(
                        "timestamp" to FieldValue.serverTimestamp()
                    )
                    dbRef.document(categoryId).update(timeStamp).addOnSuccessListener {
                        dialog?.dismiss()
                    }
                }

        }
    }

    private fun uploadCategoryImage(
        catImageUri: Uri?,
        categoryName: String,
        categoryId: String
    ) {

            var catImageRef: StorageReference? = storageReference.child("CategoryImage")
                .child(currentUser).child("$categoryName.jpg")
            var uploadTask = catImageUri?.let { it -> catImageRef?.putFile(it) }
            val urlTask = uploadTask?.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                catImageRef?.downloadUrl
            }?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    catImage = task.result
                    addCategory(categoryId, categoryName, catImage.toString())

                    Log.d("DownloadUrl", catImage.toString())
                } else {
                    Log.d("Image adder", task.exception?.message)

                }
            }

        Toast.makeText(activity, "Category Added", Toast.LENGTH_SHORT)
            .show()

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


