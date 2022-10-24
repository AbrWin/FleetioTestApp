package com.abrsoftware.fletiotestapp.domain.repository

import com.abrsoftware.fletiotestapp.domain.comment.Comment
import com.abrsoftware.fletiotestapp.domain.util.Resource

interface CommentRepository {
    suspend fun getComments(idVehicle: String):Resource<List<Comment>>
}