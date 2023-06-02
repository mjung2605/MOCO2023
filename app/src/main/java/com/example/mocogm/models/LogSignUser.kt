package com.example.mocogm.models

import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


/*enum class Type {
    GESUCHT, GEFUNDEN
}*/

class RepoPlaceholder() {

    var auth: FirebaseAuth = Firebase.auth

}

 class User(val email: String? = null, val password: String? = null)


