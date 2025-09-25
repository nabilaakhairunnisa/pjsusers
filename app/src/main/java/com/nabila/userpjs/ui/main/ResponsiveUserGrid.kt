package com.nabila.userpjs.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.nabila.userpjs.data.remote.model.UsersItem
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ResponsiveUserGrid(
    users: List<UsersItem>,
    onCardClick: (UsersItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val columns = if (configuration.screenWidthDp > configuration.screenHeightDp) {
        2 // Landscape, 2 column
    } else {
        1 // Portrait, 1 column
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(users) { user ->
            UserCard(user = user, onClick = onCardClick)
        }
    }
}