package com.example.navigationtest.api.mapper

import com.example.navigationtest.api.model.PostApiModel
import com.example.navigationtest.data.model.PostModel

object PostMapper {
    fun toApiModel(dataModel: PostModel): PostApiModel = PostApiModel(
        userId = dataModel.userId,
        id = dataModel.id,
        title = dataModel.title,
        body = dataModel.body,
    )

    fun toDataModel(dataModel: PostApiModel): PostModel = PostModel(
        userId = dataModel.userId,
        id = dataModel.id,
        title = dataModel.title,
        body = dataModel.body,
    )
}
