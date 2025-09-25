package com.nabila.userpjs.ui.detail

// Jetpack Compose imports
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

// Lifecycle + state
import androidx.lifecycle.compose.collectAsStateWithLifecycle

// Project imports
import com.nabila.userpjs.data.repository.ResultState

/**
 * DetailScreen shows user details inside a Scaffold.
 * - TopAppBar with back navigation
 * - Content area shows DetailContent depending on ResultState
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    userId: Int,                  // User ID passed via navigation
    viewModel: DetailViewModel,   // Injected ViewModel
    onBack: () -> Unit            // Callback for back navigation
) {
    // Collect user state from ViewModel
    val userState = viewModel.userState.collectAsStateWithLifecycle()

    // Trigger data load when entering the screen
    LaunchedEffect(userId) {
        viewModel.getUserById(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        // Pass current state into DetailContent
        DetailContent(
            state = userState.value,
            onRetry = { viewModel.getUserById(userId) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
