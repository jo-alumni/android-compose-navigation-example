package com.example.navigationtest.core.data.datarepository

import com.example.navigationtest.core.common.di.IoDispatcher
import com.example.navigationtest.core.data.datasource.AppApiDataSource
import com.example.navigationtest.core.data.mapper.api.PostApiMapper
import com.example.navigationtest.core.domain.entity.Post
import com.example.navigationtest.core.domain.repository.PostRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PostDataRepository @Inject constructor(
    private val appApiDataSource: AppApiDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : PostRepository {
    override suspend fun getPosts(
        page: Int,
        limit: Int,
    ): List<Post> = withContext(ioDispatcher) {
        appApiDataSource
            .getPosts(page, limit)
            .map(PostApiMapper::toEntity)
    }

    override suspend fun getPost(postId: Int): Post = withContext(ioDispatcher) {
        appApiDataSource
            .getPost(postId)
            .let(PostApiMapper::toEntity)
    }

    override suspend fun deletePost(postId: Int) = withContext(ioDispatcher) {
        appApiDataSource.deletePost(postId)
    }

    override suspend fun createPost() = TODO("postApiDataSource.createPost()")
    override suspend fun patchPost() = TODO("postApiDataSource.patchPost()")
    override suspend fun putPost() = TODO("postApiDataSource.putPost()")
}
