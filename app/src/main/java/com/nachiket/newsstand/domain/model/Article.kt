package com.nachiket.newsstand.domain.model

data class Article(
    val id: String,
    val title: String,
    val link: String,
    val description: String?,
    val imageUrl: String?,
    val pubDate: String?,
    val sourceId: String?,
    val sourceIcon: String?,
    val isBookmarked: Boolean
)
