package com.example.mocogm.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.mocogm.models.*

import com.google.firebase.auth.FirebaseUser



class ItemViewModel(): ViewModel() {
    private val itemRepo = ItemRepo()

    fun addItem (type: Type, title: String, desc: String, photo: String, loc: String, user: FirebaseUser) {
        val newItem = ItemModel(type, title, desc, photo, loc, user)
        itemRepo.addItem(newItem)
    }

    fun getItem(itemID: String) = itemRepo.getItem(itemID)
}
// beide zusammenfassen oder nicht?
class ItemListViewModel(private val itemListModel: ItemListRepo = ItemListRepo()): ViewModel() {

    private val _itemList by lazy { MutableLiveData(itemListModel.listOfItems) }
    val itemList: LiveData<List<ItemModel>?> = _itemList


    private val _selectedItem = MutableLiveData<ItemModel>() // um die ID (String) mit navController übergeben zu können,
    val selectedItem: LiveData<ItemModel> = _selectedItem  // wenn auf ein bestimmtes item geklickt wird

    fun onItemClicked(item: ItemModel) {
        _selectedItem.value = item
    }

}