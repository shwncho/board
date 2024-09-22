package com.example.board.service.dto

import com.example.board.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

data class PostSummaryResponseDto(
    val id: Long,
    val title: String,
    val createdBy: String,
    val createdAt: String,
    val firstTag: String?,
    val likeCount: Long = 0,
)

fun Page<Post>.toSummaryResponseDto(countLike: (Long) -> Long) = PageImpl(
    content.map { it.toSummaryResponseDto(countLike) },
    pageable,
    totalElements
)

fun Post.toSummaryResponseDto(countLike: (Long) -> Long) = PostSummaryResponseDto(
    id = id,
    title = title,
    createdBy = createdBy,
    createdAt = createdAt.toString(),
    firstTag = tags.firstOrNull()?.name,
    likeCount = countLike(id)
)
