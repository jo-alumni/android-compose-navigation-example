package com.example.navigationtest.data.datarepository

import com.example.navigationtest.data.datasource.AppApiDataSource
import com.example.navigationtest.data.mapper.api.CommentApiMapper
import com.example.navigationtest.domain.entity.Comment
import com.example.navigationtest.domain.repository.CommentRepository
import jakarta.inject.Inject

class CommentDataRepository @Inject constructor(
    private val appApiDataSource: AppApiDataSource,
) : CommentRepository {
    override suspend fun getComments(postId: Int, page: Int, limit: Int): List<Comment> =
        appApiDataSource
            .getCommentsRelatedPost(postId, page, limit)
            .map(CommentApiMapper::toEntity)
}
