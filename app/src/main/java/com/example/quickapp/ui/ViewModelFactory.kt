package com.example.quickapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickapp.data.LoginDataSource
import com.example.quickapp.data.LoginRepository
import com.example.quickapp.data.RepositoryListRepository
import com.example.quickapp.ui.RepositoryList.RepositoryListViewModel
import com.example.quickapp.ui.login.LoginViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                    loginRepository = LoginRepository(
                            dataSource = LoginDataSource()
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