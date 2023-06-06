package com.example.mocogm.models

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.security.auth.callback.Callback

sealed class AuthState {
    object AuthIdle : AuthState()
    object AuthLoading : AuthState()
    object AuthSuccess : AuthState()
    class AuthError(val errorMessage: String? = null) : AuthState()
}

// Brauchen wir das wenn FIrebaseUser existiert?
//abstract class User(val email: String, val password: String): FirebaseUser()


class UserRepository {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // guckt ob die eingegebene email einem email-Pattern entspricht
    fun isEmailValid(email: String) =
        Regex("^([A-Za-z0-9_.-])+@([A-Za-z0-9_-])+\\.([A-Za-z]{2,})$").matches(email)

    fun isPasswordLongEnough(password: String) = password.length > 7

    fun doesPasswordContainNumber(password: String) = password.contains(Regex("[0-9]"))

    fun doPWsMatch(password: String, pwConfirm: String) = password == pwConfirm

    private val _loginResult = MutableLiveData<AuthState>()
    val loginResult: LiveData<AuthState> get() = _loginResult

    fun logInWithEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginResult.value = AuthState.AuthSuccess
                } else {
                    val errorMessage = task.exception?.message ?: "Login failed"
                    _loginResult.value = AuthState.AuthError(errorMessage)
                }
            }
    }

    fun signUpWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginResult.value = AuthState.AuthSuccess
                } else {
                    val errorMessage = task.exception?.message ?: "Login failed"
                    _loginResult.value = AuthState.AuthError(errorMessage)
                }
            }
    }

    fun currentUser(): FirebaseUser = auth.currentUser ?: throw java.lang.Exception("Kein angemeldeter Benutzer gefunden")


}



