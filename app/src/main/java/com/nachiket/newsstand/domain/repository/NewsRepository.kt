package com.nachiket.newsstand.domain.repository

import com.nachiket.newsstand.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getHeadlines(): Flow<List<Article>>
    fun getBookmarkedArticles(): Flow<List<Article>>
    suspend fun refreshHeadlines(): Result<Unit>
    suspend fun toggleBookmark(articleId: String, isBookmarked: Boolean)
}