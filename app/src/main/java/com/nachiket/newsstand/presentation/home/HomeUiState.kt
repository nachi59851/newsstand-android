package com.nachiket.newsstand.presentation.home

import com.nachiket.newsstand.domain.model.Article

sealed class HomeUiState {

    object Loading: HomeUiState()
    data class Success(val articles: List<Article>): HomeUiState()
    data class Error(val message: String): HomeUiState()
}