package com.example.navigationtest.core.data.datarepository

import com.example.navigationtest.core.common.di.IoDispatcher
import com.example.navigationtest.core.data.datasource.AppApiDataSource
import com.example.navigationtest.core.data.mapper.api.CommentApiMapper
import com.example.navigationtest.core.domain.entity.Comment
import com.example.navigationtest.core.domain.repository.CommentRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CommentDataRepository @Inject constructor(
    private val appApiDataSource: AppApiDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CommentRepository {
    override suspend fun getComments(
        postId: Int,
        page: Int,
        limit: Int,
    ): List<Comment> = withContext(ioDispatcher) {
        appApiDataSource
            .getCommentsRelatedPost(postId, page, limit)
            .map(CommentApiMapper::toEntity)
    }
}
