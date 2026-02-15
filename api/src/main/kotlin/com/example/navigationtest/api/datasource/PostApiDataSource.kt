package com.example.navigationtest.api.datasource

import com.example.navigationtest.api.model.CommentApiModel
import com.example.navigationtest.api.model.PostApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import javax.inject.Inject

class PostApiDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getPosts(page: Int, limit: Int): List<PostApiModel> = httpClient.get {
        url(path = "posts")
        parameter("page", page)
        parameter("limit", limit)
    }.body()

    suspend fun getPost(postId: Int): PostApiModel = httpClient.get {
        url(path = "posts/$postId")
    }.body()

    suspend fun deletePost(postId: Int) {
        httpClient.delete {
            url(path = "posts/$postId")
        }
    }

    suspend fun getCommentsRelatedPost(postId: Int): List<CommentApiModel> = httpClient.get {
        url(path = "posts/$postId/comments")
    }.body()

    suspend fun createPost(post: PostApiModel) {
        httpClient.post {
            url(path = "posts")
            setBody(post)
        }
    }

    suspend fun patchPost(post: PostApiModel) {
        httpClient.patch {
            url(path = "posts/${post.id}")
            setBody(post)
        }
    }

    suspend fun putPost(post: PostApiModel) {
        httpClient.put {
            url(path = "posts/${post.id}")
            setBody(post)
        }
    }
}
