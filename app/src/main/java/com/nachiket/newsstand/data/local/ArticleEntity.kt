package com.nachiket.newsstand.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey()
    val articleId: String,
    val title: String,
    val link: String,
    val description: String?,
    val imageUrl: String?,
    val pubDate: String?,
    val sourceId: String?,
    val sourceIcon: String?,
    val isBookmarked: Boolean = false
)