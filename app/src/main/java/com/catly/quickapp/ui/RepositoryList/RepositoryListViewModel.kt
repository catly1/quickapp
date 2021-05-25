package com.catly.quickapp.ui.RepositoryList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catly.quickapp.data.RepositoryListRepository
import com.catly.quickapp.data.model.RepositoryListItem
import kotlinx.coroutines.launch
import java.lang.Exception

class RepositoryListViewModel(private val repositoryListRepository: RepositoryListRepository) : ViewModel() {
    val repoList = MutableLiveData<List<RepositoryListItem>>()

    init {
            viewModelScope.launch {
                try {
                    repoList.value = repositoryListRepository.getRepositories()
                } catch (e: Exception){

                }
            }
        }

}