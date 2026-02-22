package com.example.navigationtest.core.data.mapper.api

import com.example.navigationtest.core.data.model.PostModel
import com.example.navigationtest.core.domain.entity.Post

object PostApiMapper {
    fun toModel(entity: Post): PostModel = PostModel(
        id = entity.id,
        userId = entity.userId,
        title = entity.title,
        body = entity.body,
    )

    fun toEntity(apiModel: PostModel): Post = Post(
        id = apiModel.id,
        userId = apiModel.userId,
        title = apiModel.title,
        body = apiModel.body,
    )
}
