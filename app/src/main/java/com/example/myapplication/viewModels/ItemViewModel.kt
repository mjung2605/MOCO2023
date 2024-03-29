package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ItemViewModel : ViewModel() {

    //State-Objekte für andere Textfelder hier
    val gefundenTitleEingabe = mutableStateOf("")
    val gefundenBeschreibungEingabe = mutableStateOf("")
    val gesuchtTitleEingabe = mutableStateOf("")
    val gesuchtBeschreibungEingabe = mutableStateOf("")


    fun gefundenTitel(newTitle: String) {
        gefundenTitleEingabe.value = newTitle
    }
    fun gefundenBeschreibung(newDescription: String) {
        gefundenBeschreibungEingabe.value = newDescription
    }
    fun gesuchtTitel(newTitle: String) {
        gesuchtTitleEingabe.value = newTitle
    }
    fun gesuchtBeschreibung(newDescription: String) {
        gesuchtBeschreibungEingabe.value = newDescription
    }

    fun saveData(newItem: NewItem) {
        val database = Firebase.database
        val itemsRef = database.getReference("items")
        val newItemRef = itemsRef.push()

        newItemRef.setValue(newItem)
    }
}