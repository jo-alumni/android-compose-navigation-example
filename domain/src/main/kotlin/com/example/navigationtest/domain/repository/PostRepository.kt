package com.example.navigationtest.domain.repository

import com.example.navigationtest.domain.entity.Post

interface PostRepository {
    suspend fun getPosts(page: Int, limit: Int): List<Post>
    suspend fun getPost(postId: Int): Post
    suspend fun deletePost(postId: Int)
    suspend fun createPost()
    suspend fun patchPost()
    suspend fun putPost()
}
