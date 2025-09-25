package com.nabila.userpjs.ui.detail

// Jetpack Compose imports
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.draw.clip

// Coil for image loading
import coil.compose.AsyncImage

// Project imports
import com.nabila.userpjs.R
import com.nabila.userpjs.data.remote.model.UsersItem
import com.nabila.userpjs.data.repository.ResultState
import com.nabila.userpjs.ui.component.ErrorView
import com.nabila.userpjs.ui.component.LoadingIndicator
import com.nabila.userpjs.ui.utils.formatBirthDate

/**
 * Displays user details depending on the ResultState.
 * - Loading → Show loading indicator
 * - Success → Show user detail information
 * - Error   → Show error view with retry button
 */

@Composable
fun DetailContent(
    state: ResultState<UsersItem>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is ResultState.Loading -> LoadingIndicator(modifier = modifier)

        is ResultState.Success -> {
            val user = state.data
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // User full name and job info
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "${user.company.title} at ${user.company.name}",
                    style = MaterialTheme.typography.bodyLarge
                )

                // Profile picture + contact info
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AsyncImage(
                        model = user.image,
                        contentDescription = "User Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Phone", fontWeight = FontWeight.Bold)
                        Text(user.phone)
                        Text("Email", fontWeight = FontWeight.Bold)
                        Text(user.email)
                    }
                }

                // Age info
                Column {
                    Text("Age", fontWeight = FontWeight.Bold)
                    Text("${user.age} years old")
                }

                // Birthdate info (formatted)
                Column {
                    Text("Birthdate", fontWeight = FontWeight.Bold)
                    Text(formatBirthDate(user.birthDate))
                }
            }
        }

        is ResultState.Error -> ErrorView(
            message = stringResource(id = state.messageId),
            onRetry = onRetry
        )
    }
}
