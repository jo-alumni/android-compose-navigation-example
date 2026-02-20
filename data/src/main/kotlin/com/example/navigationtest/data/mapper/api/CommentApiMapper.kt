package com.example.navigationtest.data.mapper.api

import com.example.navigationtest.data.model.CommentModel
import com.example.navigationtest.domain.entity.Comment

object CommentApiMapper {
    fun toEntity(apiModel: CommentModel): Comment = Comment(
        id = apiModel.id,
        postId = apiModel.postId,
        name = apiModel.name,
        email = apiModel.email,
        body = apiModel.body,
    )

    fun toModel(entity: Comment): CommentModel = CommentModel(
        postId = entity.postId,
        id = entity.id,
        name = entity.name,
        email = entity.email,
        body = entity.body,
    )
}
