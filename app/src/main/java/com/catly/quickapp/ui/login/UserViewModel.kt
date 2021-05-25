package com.catly.quickapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.quickapp.R
import com.catly.quickapp.data.LoginRepository
import com.catly.quickapp.data.Result


class UserViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _logged = MutableLiveData(false)
    val logged : LiveData<Boolean> = _logged

    fun login(email: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(email, password)

        if (result is Result.Success) {
            _logged.value = true
//            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun getLoggedInUser(){
//        sharedPreferences.getString("password")
    }

    fun loginDataChanged(email: String, password: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        var hasUpperCase = false
        var hasLowerCase = false
        var hasNumber = false

        for (char in password){
            if (Character.isUpperCase(char)){
                hasUpperCase = true
            }
            if (Character.isLowerCase(char)){
                hasLowerCase = true
            }
            if (char.isDigit()){
                hasNumber = true
            }
        }
        return (password.length > 9) && hasLowerCase && hasUpperCase && hasNumber
    }
}