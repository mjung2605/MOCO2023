// ListViewModel.kt
package com.example.myapplication.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.FirebaseObject
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ListViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _firebaseObjects = MutableLiveData<List<FirebaseObject>>()
    val firebaseObjects: LiveData<List<FirebaseObject>> = _firebaseObjects


    init {
        fetchFirebaseObjects()
    }

    private fun fetchFirebaseObjects() {
        val tempList =mutableListOf<FirebaseObject>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val querySnapshot = firestore.collection("your_collection_name").get().await()
                val firebaseObjectsList = querySnapshot.documents.map { document ->
                    val title = document.getString("title") ?: ""
                    val description = document.getString("description") ?: ""
                    FirebaseObject(title, description)
                }
                _firebaseObjects.postValue(firebaseObjectsList)
            } catch (e: Exception) {
                // Handle error here, like logging or showing a toast
            }
        }
    }


}
