package com.example.mocogm.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mocogm.models.ItemModel
import com.example.mocogm.models.ItemRepo
import com.example.mocogm.models.User
import com.example.mocogm.models.UserRepository
import com.example.mocogm.models.Type

class AddItemViewModel(): ViewModel() {
    private val itemRepo = ItemRepo()

    fun addItem (type: Type, title: String, desc: String, loc: String, user: User): String {
        val newItem = ItemModel(type, title, desc, null, loc, user)
        return itemRepo.addItem(newItem)
    }

}