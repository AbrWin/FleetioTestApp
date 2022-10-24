package com.abrsoftware.fletiotestapp.view.viewmodel

import com.abrsoftware.fletiotestapp.domain.comment.Comment

data class CommentState(
    val commentList: List<Comment>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)