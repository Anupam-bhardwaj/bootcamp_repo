package com.example.anupam.utils

import android.Manifest
import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Context.isConnected(): Boolean{
    val connection: ConnectivityManager = this.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connection.activeNetworkInfo != null && connection.activeNetworkInfo.isConnected) {
        return true
    }
    return false
}

fun Context.permissionCheck(){
    if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) +
        ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) +
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=  PackageManager.PERMISSION_GRANTED){


        ActivityCompat.requestPermissions(
            this as Activity, arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.CAMERA), 1)

    }
}