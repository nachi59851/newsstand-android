package com.nachiket.newsstand.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nachiket.newsstand.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel(){

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getHeadlines().collect {articles ->
                _uiState.value = HomeUiState.Success(articles = articles)
            }
        }
        refreshHeadlines()
    }

    private fun refreshHeadlines(){
        viewModelScope.launch {
            val result = repository.refreshHeadlines()
            if (result.isFailure) {
                _uiState.value = HomeUiState.Error(message = result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

}