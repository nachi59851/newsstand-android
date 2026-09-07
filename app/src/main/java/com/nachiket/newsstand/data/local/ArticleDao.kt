package com.nachiket.newsstand.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>): List<Long>

    @Query("SELECT * FROM articles ORDER BY pubDate DESC")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE isBookmarked = 1")
    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

    @Query("UPDATE articles SET isBookmarked = :isBookmarked WHERE articleId = :articleId")
    suspend fun updateBookmarkStatus(articleId: String, isBookmarked: Boolean)
}