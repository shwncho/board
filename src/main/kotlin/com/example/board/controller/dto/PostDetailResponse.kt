package com.example.board.controller.dto

import com.example.board.service.dto.PostDetailResponseDto

data class PostDetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: String,
    val comments: List<CommentResponse> = emptyList(),
    val tags: List<String> = emptyList(),
    val likeCount: Long = 0,
)

fun PostDetailResponseDto.toResponse() = PostDetailResponse(
    id = id,
    title = title,
    content = content,
    createdBy = createdBy,
    createdAt = createdAt,
    comments = comments.map { it.toResponse() },
    likeCount = likeCount
)
