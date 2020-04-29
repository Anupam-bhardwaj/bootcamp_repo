package com.example.anupam.model

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable

data class AddCategoryModel(val catId: String?, val catImage: String?) {
    constructor(): this("", "")
}