package com.abrsoftware.fletiotestapp.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.data.model.CommentModel
import com.abrsoftware.fletiotestapp.data.remote.FleetioTestApi
import com.abrsoftware.fletiotestapp.domain.comment.Comment
import com.abrsoftware.fletiotestapp.domain.comment.toDomain
import com.abrsoftware.fletiotestapp.domain.repository.CommentRepository
import com.abrsoftware.fletiotestapp.domain.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentRepositoryImp @Inject constructor(
    private val api: FleetioTestApi,
    @ApplicationContext private val context: Context
) : CommentRepository {
    @SuppressLint("NewApi")
    override suspend fun getComments(idVehicle: String): Resource<List<Comment>> {
        return withContext(Dispatchers.IO) {
            try {
                val headers = mapOf(
                    "Authorization" to context.getString(R.string.token_auth),
                    "Account-Token" to context.getString(R.string.token_account)
                )
                val listComments = api.getListComment(idVehicle, headers)

                val finalResult = (listComments.body() as List<CommentModel>).map { it.toDomain() }
                Resource.Success(
                    data = finalResult
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message ?: "An unknow error occured.")
            }
        }
    }
}