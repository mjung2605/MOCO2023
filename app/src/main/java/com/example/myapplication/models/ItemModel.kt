package com.example.myapplication

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class DataModel(){
        private val database = Firebase.database
        private val myRef = database.getReference("item")
    
        fun saveData(newData: NewData){
            val newDataRef = myRef.push()
            newDataRef.setValue(newData)
        }
    }
data class NewItem(
    val gefundenText: String,
    val beschreibungText: String,
    //val standortText: String
)
