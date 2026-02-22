package com.example.navigationtest.domain.repository

import com.example.navigationtest.domain.entity.Comment

interface CommentRepository {
    suspend fun getComments(postId: Int, page: Int, limit: Int): List<Comment>
}
