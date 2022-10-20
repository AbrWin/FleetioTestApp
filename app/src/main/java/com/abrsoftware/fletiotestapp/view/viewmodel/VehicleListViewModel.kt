package com.abrsoftware.fletiotestapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import com.abrsoftware.fletiotestapp.pagination.VehicleDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val repository: VehicleRepository
): ViewModel() {

    val usersPager = Pager(PagingConfig(pageSize = 20)) {
        VehicleDataSource(repository)
    }.flow.cachedIn(viewModelScope)

}