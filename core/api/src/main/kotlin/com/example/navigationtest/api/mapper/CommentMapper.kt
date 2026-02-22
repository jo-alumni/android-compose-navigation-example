package com.example.navigationtest.api.mapper

import com.example.navigationtest.api.model.CommentApiModel
import com.example.navigationtest.data.model.CommentModel

object CommentMapper {
    fun toApiModel(dataModel: CommentModel): CommentApiModel = CommentApiModel(
        postId = dataModel.postId,
        id = dataModel.id,
        name = dataModel.name,
        email = dataModel.email,
        body = dataModel.body,
    )

    fun toDataModel(dataModel: CommentApiModel): CommentModel = CommentModel(
        postId = dataModel.postId,
        id = dataModel.id,
        name = dataModel.name,
        email = dataModel.email,
        body = dataModel.body,
    )
}
