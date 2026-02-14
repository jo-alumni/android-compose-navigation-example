package com.example.navigationtest.domain.mapper

import com.example.navigationtest.api.model.CommentApiModel
import com.example.navigationtest.domain.entity.Comment

object CommentApiMapper {
    fun toEntity(apiModel: CommentApiModel): Comment = Comment(
        id = apiModel.id,
        postId = apiModel.postId,
        name = apiModel.name,
        email = apiModel.email,
        body = apiModel.body,
    )

    fun toApiModel(entity: Comment): CommentApiModel = CommentApiModel(
        postId = entity.postId,
        id = entity.id,
        name = entity.name,
        email = entity.email,
        body = entity.body,
    )
}
