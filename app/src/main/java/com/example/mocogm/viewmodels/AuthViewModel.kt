package com.example.mocogm.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mocogm.models.AuthState
import com.example.mocogm.models.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthViewModel(private val userRepo: UserRepository = UserRepository()): ViewModel() {

    private val _authState by lazy { MutableLiveData<AuthState>() }
    val authState: LiveData<AuthState> = _authState

    init {
        _authState.value = AuthState.AuthIdle
    }

    fun signUpWithEmail(
        email: String,
        password: String,
        pwConfirm: String
    ) {

        if (!userRepo.isEmailValid(email)) {
            _authState.value = AuthState.AuthError("Invalide E-Mail")
            return
        }
        if(!userRepo.isPasswordLongEnough(password)) {
            _authState.value = AuthState.AuthError("Passwort ist zu kurz")
            return
        }
        if(!userRepo.doesPasswordContainNumber(password)) {
            _authState.value = AuthState.AuthError("Passwort muss mindestens eine Zahl enthalten")
            return
        }
        if(!userRepo.doPWsMatch(password, pwConfirm)) {
            _authState.value = AuthState.AuthError("Passwörter stimmen nicht überein")
            return
        }
        userRepo.signUpWithEmail(email, password)
        when(userRepo.loginResult.value) {
            is AuthState.AuthError -> _authState.value = AuthState.AuthError(errorMessage = "Fehler beim Einloggen")
            is AuthState.AuthSuccess -> _authState.value = AuthState.AuthSuccess
            AuthState.AuthIdle -> _authState.value = AuthState.AuthIdle
            AuthState.AuthLoading -> _authState.value = AuthState.AuthLoading

            else -> {}
        }
    }

    fun logInWithEmail (email: String, password: String) {
        if (!userRepo.isEmailValid(email)) {
            _authState.value = AuthState.AuthError("Invalide E-Mail")
            return
        }

        userRepo.logInWithEmail(email, password)
        // nicht schön
        when(userRepo.loginResult.value) {
            is AuthState.AuthError -> _authState.value = AuthState.AuthError(errorMessage = "Fehler beim Einloggen")
            is AuthState.AuthSuccess -> _authState.value = AuthState.AuthSuccess
            AuthState.AuthIdle -> _authState.value = AuthState.AuthIdle
            AuthState.AuthLoading -> _authState.value = AuthState.AuthLoading

            else -> {}
        }
    }

    fun getCurrentlyLoggedInUser(): FirebaseUser {

        return userRepo.currentUser()
    }
}