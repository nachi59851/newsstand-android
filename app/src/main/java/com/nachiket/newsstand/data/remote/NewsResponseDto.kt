package com.nachiket.newsstand.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    val status: String,
    val totalResults: Int,
    val results: List<ArticleDto>
)

@Serializable
data class ArticleDto(
    @SerialName("article_id")
    val articleId: String,

    val title: String,

    val link: String,

    val description: String? = null,

    val content: String? = null,

    @SerialName("pubDate")
    val pubDate: String,

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("source_id")
    val sourceId: String? = null,

    @SerialName("source_url")
    val sourceUrl: String? = null,

    @SerialName("source_icon")
    val sourceIcon: String? = null,

    val language: String? = null,

    val country: List<String>? = null,

    val category: List<String>? = null
)