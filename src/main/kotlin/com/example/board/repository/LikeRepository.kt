package com.example.board.repository

import com.example.board.domain.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<Like, Long> {
    fun countByPostId(postId: Long): Long
}
