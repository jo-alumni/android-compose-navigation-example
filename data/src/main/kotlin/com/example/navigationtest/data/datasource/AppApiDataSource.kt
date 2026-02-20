package com.example.navigationtest.data.datasource

import com.example.navigationtest.data.model.CommentModel
import com.example.navigationtest.data.model.PostModel

interface AppApiDataSource {
    suspend fun getPosts(page: Int, limit: Int): List<PostModel>
    suspend fun getPost(postId: Int): PostModel
    suspend fun deletePost(postId: Int)
    suspend fun getCommentsRelatedPost(postId: Int, page: Int, limit: Int): List<CommentModel>
    suspend fun createPost(post: PostModel)
    suspend fun patchPost(post: PostModel)
    suspend fun putPost(post: PostModel)
}
