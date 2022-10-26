package com.example.ps_codeexercise.ui.scope.driverscreen

import androidx.lifecycle.ViewModel
import com.example.ps_codeexercise.domain.entity.RouteEntity
import com.example.ps_codeexercise.domain.usecase.GetRoutesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DriversScreenViewModel(
    private val getRoutesUseCase: GetRoutesUseCase
) : ViewModel() {
    val routesState: Flow<List<RouteEntity>> = flow { emit(getRoutesUseCase()) }
}