package com.example.anupam

import androidx.lifecycle.LiveData

data class UserModel(val name: String?, val email: String?){

    constructor() : this("", "")

}