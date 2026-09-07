package com.nachiket.newsstand.data.repository

import com.nachiket.newsstand.data.local.ArticleDao
import com.nachiket.newsstand.data.mapper.toDomain
import com.nachiket.newsstand.data.mapper.toEntity
import com.nachiket.newsstand.data.remote.NewsApiService
import com.nachiket.newsstand.domain.model.Article
import com.nachiket.newsstand.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
    private val articleDao: ArticleDao,
    @Named("apiKey")
    private val apiKey: String
) : NewsRepository {

    override fun getHeadlines(): Flow<List<Article>> {
        return articleDao.getAllArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return articleDao.getBookmarkedArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun refreshHeadlines(): Result<Unit> {
        return try {
            val response = apiService.getLatestNews(apikey = apiKey)
            val bookmarkedIds = articleDao.getBookmarkedArticles().first()
                .map { it.articleId }
                .toSet()

            val entities = response.results.map { dto ->
                dto.toEntity().copy(isBookmarked = dto.articleId in bookmarkedIds)
            }

            articleDao.insertArticles(entities)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun toggleBookmark(articleId: String, isBookmarked: Boolean) {
        articleDao.updateBookmarkStatus(articleId, isBookmarked)
    }


}