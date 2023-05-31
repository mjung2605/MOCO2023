package com.example.mocogm.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

////////// VIEW MODEL FOR LOGIN / AUTHENTIFICATION ///////////

sealed class AuthState {
    object AuthIdle: AuthState()
    object AuthLoading: AuthState()
    object AuthSuccess: AuthState()
    class AuthError(val errorMessage: String? = null): AuthState()
}

class AuthViewModel: ViewModel() {

    private val _authState by lazy { MutableLiveData<AuthState>() }
    val authState: LiveData<AuthState> = _authState

    // guckt ob die eingegebene email einem email-Pattern entspricht
    fun isEmailValid(email: String) = Regex("^([A-Za-z0-9_.-])+@([A-Za-z0-9_-])+\\.([A-Za-z]{2,})$").matches(email)


    fun onLogIn(email: String, password: String) {

        if (!isEmailValid(email)) {
            _authState.value = AuthState.AuthError("Invalid email")
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i(TAG, "Einloggen erfolgreich")
                    _authState.value = AuthState.AuthSuccess
                }
                else {
                    task.exception?.let {
                        Log.i(TAG, "Einloggen fehlgeschlagen mit Fehler ${it.localizedMessage}")
                        _authState.value = AuthState.AuthError(it.localizedMessage)
                    }
                }
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i(TAG,"Accounterstellung erfolgreich")
                    _authState.value = AuthState.AuthSuccess
                } else {
                    task.exception?.let {
                        Log.i(TAG,"Accounterstellung fehlgeschlagen mit Fehler ${it.localizedMessage}")
                        _authState.value = AuthState.AuthError(it.localizedMessage)
                    }
                }
            }
    }

    fun onSignIn(email: String, password: String, confirmPassword: String) {

        if (!isEmailValid(email)) {
            _authState.value = AuthState.AuthError("Invalid email")
            return
        }
        if (password != confirmPassword) {
            _authState.value = AuthState.AuthError("Password does not match")
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i(TAG,"Email signup is successful")
                    _authState.value = AuthState.AuthSuccess
                } else {
                    task.exception?.let {
                        Log.i(TAG,"Email signup failed with error ${it.localizedMessage}")
                        _authState.value = AuthState.AuthError(it.localizedMessage)
                    }
                }
            }
    }


}