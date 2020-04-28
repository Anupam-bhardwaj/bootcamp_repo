package com.example.anupam.model

import androidx.lifecycle.LiveData

data class UserModel(val name: String?, val email: String?){

    constructor() : this("", "")

}