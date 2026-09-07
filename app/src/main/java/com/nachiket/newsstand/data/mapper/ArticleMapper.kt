package com.nachiket.newsstand.data.mapper

import com.nachiket.newsstand.data.local.ArticleEntity
import com.nachiket.newsstand.data.remote.ArticleDto
import com.nachiket.newsstand.domain.model.Article

fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        articleId = this.articleId,
        title = this.title,
        link = this.link,
        description = this.description,
        imageUrl = this.imageUrl,
        pubDate = this.pubDate,
        sourceId = this.sourceId,
        sourceIcon = this.sourceIcon,
        isBookmarked = false
    )
}

fun ArticleEntity.toDomain(): Article {
    return Article(
        id = this.articleId,
        title = this.title,
        link = this.link,
        description = this.description,
        imageUrl = this.imageUrl,
        pubDate = this.pubDate,
        sourceId = this.sourceId,
        sourceIcon = this.sourceIcon,
        isBookmarked = this.isBookmarked
    )
}