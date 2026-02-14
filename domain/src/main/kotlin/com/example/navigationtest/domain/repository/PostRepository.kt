package com.example.navigationtest.domain.repository

import com.example.navigationtest.api.datasource.PostApiDataSource
import com.example.navigationtest.domain.entity.Comment
import com.example.navigationtest.domain.entity.Post
import com.example.navigationtest.domain.mapper.CommentApiMapper
import com.example.navigationtest.domain.mapper.PostApiMapper
import jakarta.inject.Inject

class PostRepository @Inject constructor(
    private val postApiDataSource: PostApiDataSource,
) {
    suspend fun getPosts(): List<Post> =
        postApiDataSource
            .getPosts()
            .map(PostApiMapper::toEntity)

    suspend fun getComments(postId: Int): List<Comment> =
        postApiDataSource
            .getCommentsRelatedPost(postId)
            .map(CommentApiMapper::toEntity)

    suspend fun getPost(postId: Int): Post =
        postApiDataSource
            .getPost(postId)
            .let(PostApiMapper::toEntity)

    suspend fun deletePost(postId: Int) =
        postApiDataSource.deletePost(postId)

    // suspend fun createPost() = postApiDataSource.createPost()
    // suspend fun patchPost() = postApiDataSource.patchPost()
    // suspend fun putPost() = postApiDataSource.putPost()
}
