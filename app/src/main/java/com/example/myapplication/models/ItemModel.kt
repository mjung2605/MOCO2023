package com.example.myapplication

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class NewItem(
    val gefundenText: String,
    val beschreibungText: String,
    //val standortText: String
)