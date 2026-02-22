package com.example.navigationtest.core.api.provider

import com.example.navigationtest.core.api.mapper.CommentMapper
import com.example.navigationtest.core.api.mapper.PostMapper
import com.example.navigationtest.core.api.model.CommentApiModel
import com.example.navigationtest.core.api.model.PostApiModel
import com.example.navigationtest.core.data.datasource.AppApiDataSource
import com.example.navigationtest.core.data.model.CommentModel
import com.example.navigationtest.core.data.model.PostModel
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

class AppApiDataProvider @Inject constructor(
    private val httpClient: HttpClient,
) : AppApiDataSource {
    override suspend fun getPosts(page: Int, limit: Int): List<PostModel> =
        withContext(Dispatchers.IO) {
            httpClient.get {
                url(path = "posts")
                parameter("_page", page)
                parameter("_limit", limit)
            }.body<List<PostApiModel>>()
                .map(PostMapper::toDataModel)
        }

    override suspend fun getPost(postId: Int): PostModel = withContext(Dispatchers.IO) {
        httpClient.get {
            url(path = "posts/$postId")
        }.body<PostApiModel>()
            .let(PostMapper::toDataModel)
    }

    override suspend fun deletePost(postId: Int) {
        withContext(Dispatchers.IO) {
            httpClient.delete {
                url(path = "posts/$postId")
            }
        }
    }

    override suspend fun getCommentsRelatedPost(postId: Int, page: Int, limit: Int): List<CommentModel> = withContext(Dispatchers.IO) {
        httpClient.get {
            url(path = "posts/$postId/comments")
            parameter("_page", page)
            parameter("_limit", limit)
        }.body<List<CommentApiModel>>()
            .map(CommentMapper::toDataModel)
    }

    override suspend fun createPost(post: PostModel) {
        withContext(Dispatchers.IO) {
            httpClient.post {
                url(path = "posts")
                setBody(post.let(PostMapper::toApiModel))
            }
        }
    }

    override suspend fun patchPost(post: PostModel) {
        withContext(Dispatchers.IO) {
            httpClient.patch {
                url(path = "posts/${post.id}")
                setBody(post.let(PostMapper::toApiModel))
            }
        }
    }

    override suspend fun putPost(post: PostModel) {
        withContext(Dispatchers.IO) {
            httpClient.put {
                url(path = "posts/${post.id}")
                setBody(post.let(PostMapper::toApiModel))
            }
        }
    }
}
