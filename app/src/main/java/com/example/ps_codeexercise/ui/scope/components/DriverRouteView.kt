package com.example.ps_codeexercise.ui.scope.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ps_codeexercise.domain.entity.RouteEntity

@Composable
fun DriverRouteView(
    route: RouteEntity,
    isSelected: Boolean,
    onClick: (RouteEntity?) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 10.dp,
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick(if (isSelected) null else route) }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(24.dp)
        ) {

            Text(
                text = route.driver.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            AnimatedVisibility(
                visible = isSelected,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                Text(
                    text = route.shipment.title,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.Start)
                )
            }
        }
    }
}