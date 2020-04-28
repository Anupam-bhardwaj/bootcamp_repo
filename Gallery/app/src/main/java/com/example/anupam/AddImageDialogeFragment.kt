package com.example.anupam

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.anupam.model.ImageModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream


class AddImageDialogeFragment : DialogFragment() {

//    lateinit var displayImage: ImageView

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var storageReference = FirebaseStorage.getInstance().reference
    private var currentUser: String = mAuth.currentUser?.uid.toString()

    private var imageUri: Uri? = null
    private var uploadedImage: Uri? = null
    //    var dbRef = db.collection("Categories").document(currentUser).collection("category")
//    var dbRef = db.collection("Users").document("Categories").collection(currentUser)
    val dbRef = db.collection("Users").document("Timeline").collection(currentUser)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val positionBundel: Bundle? = arguments
        var categoryName: String = positionBundel?.get("categoryName") as String
        val rootView: View = inflater.inflate(R.layout.fragment_add_image_dialoge, container, false)
        Log.d("AddImageDialog", categoryName)
        val takePhotoBtn: AppCompatButton = rootView.findViewById(R.id.takePhoto)
        val selectPhotoBtn: AppCompatButton = rootView.findViewById(R.id.selectImage)
//        displayImage = rootView.findViewById(R.id.displayImage)
        val addBtn: AppCompatButton = rootView.findViewById(R.id.addDialogBtn)
        val cancelBtn: AppCompatButton = rootView.findViewById(R.id.cancelDialogBtn)

        cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }

        takePhotoBtn.setOnClickListener {
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
//            var mFile = File(android.os.Environment.getExternalStorageDirectory().toString())
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile))
            startActivityForResult(intent, 1)
            takePhotoBtn.isEnabled = false
            selectPhotoBtn.isEnabled = false
        }

        selectPhotoBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 2)
            selectPhotoBtn.isEnabled = false
            takePhotoBtn.isEnabled = false
        }

        addBtn.setOnClickListener {
            imageUri?.let { uploadeImage(categoryName, it) }
        }

        return rootView
    }

    private fun uploadeImage(categoryName: String, imageUri: Uri) {

        val imageRef: StorageReference = storageReference.child("timeline")
            .child(currentUser).child(getRandomString(10)+".jpg")
        var uploadTask = imageUri?.let { it -> imageRef?.putFile(it) }
        val urlTask = uploadTask?.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef?.downloadUrl
        }?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                uploadedImage = task.result
                addCategory(categoryName, uploadedImage.toString())

                Toast.makeText(activity, "Category Added", Toast.LENGTH_SHORT)
                    .show()

                Log.d("DownloadUrl", uploadedImage.toString())
            } else {
                Log.d("Image adder", task.exception?.message)

            }
        }

    }

    private fun addCategory(categoryName: String, uploadedImageUrl: String) {

        val imageId = getRandomString(20)
        val imageModel: ImageModel =
            ImageModel(
                imageId,
                categoryName,
                uploadedImageUrl
            )
        activity.let {
            dbRef.document(imageId).set(imageModel).addOnSuccessListener {
                val timeStamp = hashMapOf<String, Any>(
                    "timestamp" to FieldValue.serverTimestamp()
                )
                dbRef.document(imageId).update(timeStamp).addOnSuccessListener {
                    dialog?.dismiss()
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            when(requestCode){
                1 -> {
                        //code Working
//                        var photo: Bitmap = data?.extras?.get("data") as Bitmap

                    var photo: Bitmap = data?.extras?.get("data") as Bitmap
                    imageUri = getImageUri(context, photo)
                    Log.d("onActivityResult", imageUri.toString())

                }
                2 -> {
                    imageUri = data?.data!!
                }
            }
        }
    }

    private fun getImageUri(context: Context?, photo: Bitmap): Uri {
        val bytes: ByteArrayOutputStream = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(context?.contentResolver, photo, getRandomString(8), null)
        val image = Uri.parse(path)
        Log.d("ImageUri", image.path.toString())
        return image

    }

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
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
