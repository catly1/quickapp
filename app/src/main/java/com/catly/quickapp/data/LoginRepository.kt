package com.catly.quickapp.data

import android.app.Application
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.catly.quickapp.data.model.LoggedInUser
import java.lang.Exception

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(application: Application) {

    private var sharedPreferences: SharedPreferences

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    init {
        val spec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .build()

        val masterKey: MasterKey = MasterKey.Builder(application.applicationContext)
            .setKeyGenParameterSpec(spec)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            application.applicationContext,
            "secret_shared_prefs",
            masterKey, // masterKey created above
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );

        getUser()
    }

    fun logout() {
        user = null
        sharedPreferences.edit()
            .putString("email", null)
            .putString("password", null)
            .apply()
    }

    fun login(email: String, password: String): Result<LoggedInUser> {
        return try {
            sharedPreferences.edit()
                .putString("email", email)
                .putString("password", password)
                .apply()
            val user = LoggedInUser(email,password)
            setLoggedInUser(user)
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }

    private fun getUser() {
        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)

        if (email != null && password != null) {
            setLoggedInUser(LoggedInUser(email,password))
        }
    }
}