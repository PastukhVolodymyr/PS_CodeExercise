package com.example.ps_codeexercise.di

import com.example.ps_codeexercise.data.repository.DriversRepository
import com.example.ps_codeexercise.data.repository.DriversRepositoryImpl
import com.example.ps_codeexercise.data.repository.ShipmentsRepository
import com.example.ps_codeexercise.data.repository.ShipmentsRepositoryImpl
import com.example.ps_codeexercise.data.source.DriversDataSource
import com.example.ps_codeexercise.data.source.DriversFileDataSource
import com.example.ps_codeexercise.data.source.ShipmentsDataSource
import com.example.ps_codeexercise.data.source.ShipmentsFileDataSource
import com.example.ps_codeexercise.data.utils.JsonRawResource
import com.example.ps_codeexercise.domain.usecase.GetRoutesUseCase
import com.example.ps_codeexercise.domain.usecase.RouteUseCaseImplGet
import com.example.ps_codeexercise.ui.scope.driverscreen.DriversScreenViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single(named(DispatcherToken.IO)) { Dispatchers.IO }
    single(named(DispatcherToken.DEFAULT)) { Dispatchers.Default }

    factory { Gson() }
    factory { JsonRawResource(androidContext()) }

    single<DriversDataSource> { DriversFileDataSource(get(), get()) }
    single<ShipmentsDataSource> { ShipmentsFileDataSource(get(), get()) }
    single<DriversRepository> { DriversRepositoryImpl(get(), get(named(DispatcherToken.IO))) }
    single<ShipmentsRepository> { ShipmentsRepositoryImpl(get(), get(named(DispatcherToken.IO))) }
    single<GetRoutesUseCase> {
        RouteUseCaseImplGet(
            get(),
            get(),
            get(named(DispatcherToken.DEFAULT))
        )
    }

    viewModel { DriversScreenViewModel(get()) }
}

private enum class DispatcherToken {
    IO,
    DEFAULT
}