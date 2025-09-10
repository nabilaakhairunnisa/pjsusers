package com.nabila.userpjs.ui.main

// Compose & Material imports
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nabila.userpjs.R

// Project imports
import com.nabila.userpjs.data.remote.model.UsersItem

/**
 * MainScreen is the entry point for showing users list.
 * It includes a TopAppBar, SearchBar, SortMenu, and content area.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onCardClick: (UsersItem) -> Unit
) {
    val userState = viewModel.userState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Users") },

                    // more
                    actions = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = stringResource(R.string.more_options)
                            )
                        }

                        //sort
                        SortMenu(
                            expanded = expanded,
                            onDismiss = { expanded = false },
                            onSortSelected = { ascending ->
                                viewModel.sortByName(ascending)
                                expanded = false
                            }
                        )
                    }
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                )
            }
        }
    ) { innerPadding ->

        MainScreenContent(
            state = userState.value,
            searchQuery = searchQuery,
            onSearchChanged = { query -> viewModel.onSearchQueryChanged(query) },
            onClearSearch = { viewModel.onSearchQueryChanged("") },
            onRetry = { viewModel.getUsers() },
            onCardClick = onCardClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
