package com.catly.quickapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.catly.quickapp.data.LoginRepository
import com.catly.quickapp.data.RepositoryListRepository
import com.catly.quickapp.ui.RepositoryList.RepositoryListViewModel
import com.catly.quickapp.ui.login.UserViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            return UserViewModel(
                    loginRepository = LoginRepository(
                        application
                    )
            ) as T
        }

        if (modelClass.isAssignableFrom(RepositoryListViewModel::class.java)) {
            return RepositoryListViewModel(repositoryListRepository = RepositoryListRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}