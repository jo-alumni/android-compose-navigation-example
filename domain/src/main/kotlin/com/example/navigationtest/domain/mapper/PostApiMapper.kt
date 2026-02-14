package com.example.navigationtest.domain.mapper

import com.example.navigationtest.api.model.PostApiModel
import com.example.navigationtest.domain.entity.Post

object PostApiMapper {
    fun toApiModel(entity: Post): PostApiModel = PostApiModel(
        id = entity.id,
        userId = entity.userId,
        title = entity.title,
        body = entity.body,
    )

    fun toEntity(apiModel: PostApiModel): Post = Post(
        id = apiModel.id,
        userId = apiModel.userId,
        title = apiModel.title,
        body = apiModel.body,
    )
}
