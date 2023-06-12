package com.example.mocogm.models

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

sealed class ItemState {
    object ItemIdle : ItemState()
    object ItemLoading : ItemState()
    object ItemSuccess : ItemState()
    class ItemError(val errorMessage: String? = null) : ItemState()
}


enum class Type{
    GESUCHT, GEFUNDEN
}

interface Item {

    // erstmal var, weil wir uns eine Bearbeitungsfunktion offen halten
    var type: Type
    var title: String
    var desc: String
    var picture: String
    var loc: String
    var user: FirebaseUser?
    var itemId: String
}

data class ItemModel(override var type: Type,
                     override var title: String,
                     override var desc: String,
                     override var picture: String, // TODO String nur als Platzhaltertyp
                     override var loc: String,
                     override var user: FirebaseUser?,
                     override var itemId: String = ""
): Item

class ItemRepo() {

    val itemState: ItemState = ItemState.ItemIdle // können wir hiermit irgendwie gucken ob das fetchen von der Database klappt auber idk maybe not
    // TODO() sollen wir hier eine Liste mit allen Items anlegen? (hab jetzt erstmal unten neue Klasse gemacht)

    //Item ID geben und der DB hinzufügen. ID wird returned
    fun addItem(item: Item): String {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance() //gibt eine Instanz der Firebase-Datenbank zurück.
        val itemsRef: DatabaseReference = database.getReference("Item") // Oder "item" ???

        val newItemRef: DatabaseReference = itemsRef.push() // Generiert eine eindeutige ID für das neue Item
        newItemRef.setValue(item) // Speichert das Item unter der generierten ID in der Datenbank
        var itemId = newItemRef //ID lokal speichern um Item löschen zu können
        return newItemRef.key ?: ""  // Gibt die generierte ID des neuen Items zurück
    }

    fun getItem(itemID: String): Item? {
        return null
    } // TODO() GET ITEM FROM DATABASE

    //löscht die Items anhand der in der Funktion addItem erstellten ID/ref
    fun deleteItem(itemId: String) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val itemsRef: DatabaseReference = database.getReference("items")
        val itemRef: DatabaseReference = itemsRef.child(itemId)
        itemRef.removeValue()
    }

}

// vielleicht mit dem obigen zusammenfassen?
class ItemListRepo() {
    // nur kind of platzhalter idk. aber ich meine wir müssen schon live data ausgeben, es ist nur fraglich,
    // wie wir die Liste bekommen
    private val _listOfItems: List<ItemModel>? = null // TODO: Liste der Items von der Datenbank returnen. Sind die Items als Liste abfragbar oder müssen wir manuell eine kreieren?
    val listOfItems = _listOfItems

}

