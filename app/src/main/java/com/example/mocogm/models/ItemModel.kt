package com.example.mocogm.models

import android.media.Image
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


enum class Type{
    GESUCHT, GEFUNDEN
}

interface Item {
    // erstmal var, weil wir uns eine Bearbeitungsfunktion offen halten
    var type: Type
    var title: String
    var desc: String
    var picture: Image?
    var loc: String
    var user: User
    var itemId: String
}

data class ItemModel(override var type: Type,
                override var title: String,
                override var desc: String,
                override var picture: Image?,
                override var loc: String,
                override var user: User,
                override var itemId: String = ""
): Item

class ItemRepo() {
    //Item ID geben und der DB hinzufügen. ID wird returned
    fun addItem(item: Item): String {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance() //gibt eine Instanz der Firebase-Datenbank zurück.
        val itemsRef: DatabaseReference = database.getReference("ItemModel") // Oder "item" ???

        val newItemRef: DatabaseReference = itemsRef.push() // Generiert eine eindeutige ID für das neue Item
        newItemRef.setValue(item) // Speichert das Item unter der generierten ID in der Datenbank
        var itemId = newItemRef //ID lokal speichern um Item löschen zu können
        return newItemRef.key ?: "" // Gibt die generierte ID des neuen Items zurück
    }

    fun getItem(): Item = TODO()

    //löscht die Items anhand der in der Funktion addItem erstellten ID/ref
    fun deleteItem(itemId: String) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val itemsRef: DatabaseReference = database.getReference("items")
        val itemRef: DatabaseReference = itemsRef.child(itemId)
        itemRef.removeValue()
    }

}

// Testing if everything works as intended :)
fun main()  {
    /*
    val testlogin = LogSignUser("mjungilligens@gmail.com", "asdfghjkl1")
    if (testlogin.isEmailValid()) println("Email is valid") // funktioniert noch nicht
    if (testlogin.isPasswordLongerThan7()) println("pw is longer than 7")
    if (testlogin.doesPasswordContainANumber()) println("pw contains a number")

     */

    val user = User("abc@gmail.de", "asdfghj123")
    val banane = ItemModel(Type.GESUCHT, "hdjashdjkashd", "123", null, "GM", user)

    val itemRepo = ItemRepo()

    // itemRepo.deleteItem(banane.itemId)
    println(itemRepo.addItem(banane))

}