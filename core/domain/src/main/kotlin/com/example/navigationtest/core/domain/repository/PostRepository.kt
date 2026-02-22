package com.example.navigationtest.core.domain.repository

import com.example.navigationtest.core.domain.entity.Post

interface PostRepository {
    suspend fun getPosts(page: Int, limit: Int): List<Post>
    suspend fun getPost(postId: Int): Post
    suspend fun deletePost(postId: Int)
    suspend fun createPost()
    suspend fun patchPost()
    suspend fun putPost()
}
