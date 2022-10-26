package com.example.ps_codeexercise.ui.scope.driverscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ps_codeexercise.domain.entity.DriverEntity
import com.example.ps_codeexercise.domain.entity.RouteEntity
import com.example.ps_codeexercise.domain.entity.ShipmentEntity
import com.example.ps_codeexercise.ui.scope.components.DriverRouteView
import org.koin.androidx.compose.getViewModel

@Composable
fun DriverScreen(viewModel: DriversScreenViewModel = getViewModel()) {
    RoutesContentView(routesState = viewModel.routesState.collectAsState(initial = emptyList()))
}

@Composable
private fun RoutesContentView(routesState: State<List<RouteEntity>>) {
    val selectedRoute = remember { mutableStateOf<RouteEntity?>(null) }

    LazyColumn(
        contentPadding = PaddingValues(
            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        ),
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        items(routesState.value) { route ->
            DriverRouteView(
                route = route,
                isSelected = selectedRoute.value == route,
                onClick = { selectedRoute.value = it },
                modifier = Modifier.padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun DriverScreenPreview() {
    val routesState = remember {
        mutableStateOf(
            listOf(
                RouteEntity(
                    driver = DriverEntity("test driver"),
                    shipment = ShipmentEntity("test shipment")
                )
            )
        )
    }
    RoutesContentView(routesState)
}

