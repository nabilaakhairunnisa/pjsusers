package com.nabila.userpjs.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nabila.userpjs.data.remote.model.UsersItem
import com.nabila.userpjs.data.repository.ResultState
import com.nabila.userpjs.ui.component.ErrorView
import com.nabila.userpjs.ui.component.LoadingIndicator


/**
 * Content section of MainScreen:
 * - Search bar
 * - Users grid (loading/success/error states)
 */
@Composable
fun MainScreenContent(
    state: ResultState<List<UsersItem>>,
    searchQuery: String,
    onSearchChanged: (String) -> Unit,
    onClearSearch: () -> Unit,
    onRetry: () -> Unit,
    onCardClick: (UsersItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchChanged,
            onClear = onClearSearch
        )

        when (state) {
            is ResultState.Loading -> LoadingIndicator(modifier = Modifier.padding(16.dp))
            is ResultState.Success -> ResponsiveUserGrid(
                users = state.data,
                onCardClick = onCardClick,
                modifier = Modifier.padding(8.dp)
            )
            is ResultState.Error -> ErrorView(
                message = stringResource(id = state.messageId),
                onRetry = onRetry
            )
        }
    }
}