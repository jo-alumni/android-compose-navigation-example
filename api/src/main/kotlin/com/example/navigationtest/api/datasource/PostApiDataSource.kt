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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostApiDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getPosts(page: Int, limit: Int): List<PostApiModel> = withContext(Dispatchers.IO) {
        httpClient.get {
            url(path = "posts")
            parameter("_page", page)
            parameter("_limit", limit)
        }.body()
    }

    suspend fun getPost(postId: Int): PostApiModel = withContext(Dispatchers.IO) {
        httpClient.get {
            url(path = "posts/$postId")
        }.body()
    }

    suspend fun deletePost(postId: Int) {
        withContext(Dispatchers.IO) {
            httpClient.delete {
                url(path = "posts/$postId")
            }
        }
    }

    suspend fun getCommentsRelatedPost(postId: Int, page: Int, limit: Int): List<CommentApiModel> = withContext(Dispatchers.IO) {
        httpClient.get {
            url(path = "posts/$postId/comments")
            parameter("_page", page)
            parameter("_limit", limit)
        }.body()
    }

    suspend fun createPost(post: PostApiModel) {
        withContext(Dispatchers.IO) {
            httpClient.post {
                url(path = "posts")
                setBody(post)
            }
        }
    }

    suspend fun patchPost(post: PostApiModel) {
        withContext(Dispatchers.IO) {
            httpClient.patch {
                url(path = "posts/${post.id}")
                setBody(post)
            }
        }
    }

    suspend fun putPost(post: PostApiModel) {
        withContext(Dispatchers.IO) {
            httpClient.put {
                url(path = "posts/${post.id}")
                setBody(post)
            }
        }
    }
}
