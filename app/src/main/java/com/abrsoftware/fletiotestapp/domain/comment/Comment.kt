package com.abrsoftware.fletiotestapp.domain.comment

import com.abrsoftware.fletiotestapp.data.model.CommentModel

data class Comment(
    val id: String?,
    val title: String?,
    val comment: String?,
    val user_full_name: String?,
    val user_image_url: String?,
    val created_at: String?,
)

fun CommentModel.toDomain() = Comment(id, title, comment, user_full_name, user_image_url, created_at)