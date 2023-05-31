
package com.example.mocogm.viewmodels

// erste Versuche VM in MVVM

/*


import android.media.Image
import android.provider.ContactsContract.Data
import android.view.View
import androidx.compose.foundation.lazy.layout.getDefaultLazyLayoutKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mocogm.models.RepoPlaceholder
import java.lang.reflect.Array.get
import java.security.AccessControlContext
import kotlin.math.log

class AppData //

sealed class DataState {
    object Loading: DataState()
    class Success(data: AppData?) : DataState()
    class Error(val errorMessage: String): DataState()
}




class LogSignViewModel(): ViewModel() {



    // login activity can observe if there is a logged in user.
    // if there is none, this user will be set after login call.
    val user = RepoPlaceholder().getLoggedInUser()


    fun login(userName: String, password: String) {
        Re.login(userName, password)
    }
}

class LogInViewModel2(): ViewModel() {

    private val _loginState = MutableLiveData<DataState>()
    val loginState: LiveData<DataState> = _loginState


    fun onClickLogIn(email: String, password: String) {

        _loginState.value = DataState.Loading

        // check login validity
        // platzhalter für immplementierung:
        if (LogSignUser(email, password).performLogIn(email, password)) { // returns true if login successful

            _loginState.value = DataState.Success(null) // data ist null weil in diesem Fall ja nichts holen wollen oder so, nur LogIn confirmen

        } else {

            _loginState.value = DataState.Error("Fehler beim LogIn. Bitte überprüfe deine Eingabe und versuche es erneut.")
            return
        }
    }
}

class SignInViewModel(): ViewModel() {

    private val _signinState = MutableLiveData<DataState>()
    val signinState: LiveData<DataState> = _signinState

    fun onClickLogIn(email: String, password: String, navController: NavController) {

        _signinState.value = DataState.Loading

        // check login validity
        // platzhalter für immplementierung:
        if (LogSignUser(email, password).performSignIn(email, password)) { // returns true if form of email & pw is ok and account isn't duplicate
            _signinState.value = DataState.Success(null) // data ist null weil in diesem Fall ja nichts holen wollen oder so, nur SignIn confirmen
        } else {
            _signinState.value = DataState.Error("Fehler beim Erstellen des Accounts. Bitte überprüfe deine Eingabe und versuche es erneut.")
            return
        }
        navController.navigate(Screen.MainScreenTabBlue.route)
    }
}

*/
