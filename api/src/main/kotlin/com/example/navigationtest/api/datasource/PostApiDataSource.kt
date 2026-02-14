package com.example.navigationtest.api.datasource

import com.example.navigationtest.api.core.delete
import com.example.navigationtest.api.core.get
import com.example.navigationtest.api.core.patch
import com.example.navigationtest.api.core.post
import com.example.navigationtest.api.core.put
import com.example.navigationtest.api.model.CommentApiModel
import com.example.navigationtest.api.model.PostApiModel
import io.ktor.client.HttpClient
import javax.inject.Inject

class PostApiDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getPosts(): List<PostApiModel> =
        httpClient.get<List<PostApiModel>>(path = "posts")

    suspend fun getPost(postId: Int): PostApiModel =
        httpClient.get<PostApiModel>(path = "posts/$postId")

    suspend fun deletePost(postId: Int) =
        httpClient.delete<Unit>(path = "posts/$postId")

    suspend fun getCommentsRelatedPost(postId: Int) =
        httpClient.get<List<CommentApiModel>>(path = "posts/$postId/comments")

    suspend fun createPost(post: PostApiModel) =
        httpClient.post<Unit, PostApiModel>(path = "posts", body = post)

    suspend fun patchPost(post: PostApiModel) =
        httpClient.patch<Unit, PostApiModel>(path = "posts/${post.id}", body = post)

    suspend fun putPost(post: PostApiModel) =
        httpClient.put<Unit, PostApiModel>(path = "posts/${post.id}", body = post)
}
