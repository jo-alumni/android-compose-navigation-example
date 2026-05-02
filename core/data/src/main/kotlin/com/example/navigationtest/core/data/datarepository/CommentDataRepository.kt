package com.example.navigationtest.core.data.datarepository

import com.example.navigationtest.core.data.datasource.AppApiDataSource
import com.example.navigationtest.core.data.mapper.api.CommentApiMapper
import com.example.navigationtest.core.domain.entity.Comment
import com.example.navigationtest.core.domain.repository.CommentRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentDataRepository @Inject constructor(
    private val appApiDataSource: AppApiDataSource,
) : CommentRepository {
    override suspend fun getComments(
        postId: Int,
        page: Int,
        limit: Int,
    ): List<Comment> = withContext(Dispatchers.IO) {
        appApiDataSource
            .getCommentsRelatedPost(postId, page, limit)
            .map(CommentApiMapper::toEntity)
    }
}
