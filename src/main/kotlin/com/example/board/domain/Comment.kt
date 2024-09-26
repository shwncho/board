package com.example.board.domain

import com.example.board.exception.CommentNotUpdatableException
import com.example.board.service.dto.CommentUpdateRequestDto
import jakarta.persistence.ConstraintMode
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(name = "idx_post_id", columnList = "post.id")])
class Comment(
    content: String,
    post: Post,
    createdBy: String,
) : BaseEntity(createdBy = createdBy) {

    fun update(updateRequestDto: CommentUpdateRequestDto) {
        if (updateRequestDto.updatedBy != this.createdBy) {
            throw CommentNotUpdatableException()
        }
        this.content = updateRequestDto.content
        super.updatedBy(updateRequestDto.updatedBy)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var post: Post = post
        protected set
}
