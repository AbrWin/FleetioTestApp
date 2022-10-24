package com.abrsoftware.fletiotestapp.data.model

import com.google.gson.annotations.SerializedName

data class CommentModel (
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("comment") val comment: String?,
    @SerializedName("user_full_name") val user_full_name: String?,
    @SerializedName("user_image_url") val user_image_url: String?,
    @SerializedName("created_at") val created_at: String?,
)