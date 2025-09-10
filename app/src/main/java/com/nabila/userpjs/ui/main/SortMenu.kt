package com.nabila.userpjs.ui.main

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Dropdown menu for sorting options (ascending / descending).
 */
@Composable
fun SortMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onSortSelected: (Boolean) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = { Text("Sort A → Z") },
            onClick = { onSortSelected(true) }
        )
        DropdownMenuItem(
            text = { Text("Sort Z → A") },
            onClick = { onSortSelected(false) }
        )
    }
}