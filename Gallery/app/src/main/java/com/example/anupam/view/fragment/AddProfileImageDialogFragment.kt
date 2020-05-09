package com.example.anupam.view.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.anupam.R
import com.example.anupam.viewModel.AddprofileImageViewModel
import com.example.anupam.viewModel.MyViewModelFactory
import java.io.ByteArrayOutputStream


class AddProfileImageDialogFragment : DialogFragment() {

    private val mViewModel by lazy {
       ViewModelProvider(this, MyViewModelFactory()).get(AddprofileImageViewModel::class.java)

    }


    private var profileImageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_add_profile_image_dialog, container, false)

        val takePhotoBtn: AppCompatButton = view.findViewById(R.id.takePhoto)
        val selectPhotoBtn: AppCompatButton = view.findViewById(R.id.selectImage)

        val addBtn: AppCompatButton = view.findViewById(R.id.addDialogBtn)
        val cancelBtn: AppCompatButton = view.findViewById(R.id.cancelDialogBtn)

        cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }

        takePhotoBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
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
            profileImageUri?.let { it1 -> mViewModel.addProfileImage(it1) }
        }


        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                1 -> {
                    var photo: Bitmap = data?.extras?.get("data") as Bitmap
                    profileImageUri = getImageUri(context, photo)

                }
                2 -> {
                    profileImageUri = data?.data!!
                }
            }
        }
    }

    private fun getImageUri(context: Context?, photo: Bitmap): Uri {
        val bytes: ByteArrayOutputStream = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(context?.contentResolver, photo, getRandomString(8), null)
        val image = Uri.parse(path)
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
