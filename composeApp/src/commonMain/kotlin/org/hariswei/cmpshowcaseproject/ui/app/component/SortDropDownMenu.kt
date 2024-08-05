package org.hariswei.cmpshowcaseproject.ui.app.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

enum class SortType{
    RECENT,
    POPULAR
}

@Composable
fun SortDropDownMenu(
    isDisplayed: Boolean = false,
    selectedType: SortType,
    onDismiss: () -> Unit,
    onSelectType: (SortType) -> Unit
) {
    DropdownMenu(
        expanded = isDisplayed,
        onDismissRequest = { onDismiss() }
    ) {
        DropdownMenuItem(
            modifier = Modifier.testTag("dd_menu_recent"),
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Recent"
                    )
                    if (selectedType == SortType.RECENT) {
                        Icon(
                            modifier = Modifier.testTag("icon_recent_check"),
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Icon Check Recent"
                        )
                    }
                }
            },
            onClick = {
                onSelectType(SortType.RECENT)
                onDismiss()
            }
        )
        DropdownMenuItem(
            modifier = Modifier.testTag("dd_menu_popular"),
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Popular"
                    )
                    if (selectedType == SortType.POPULAR) {
                        Icon(
                            modifier = Modifier.testTag("icon_popular_check"),
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Icon Check Popular"
                        )
                    }
                }
            },
            onClick = {
                onSelectType(SortType.POPULAR)
                onDismiss()
            }
        )
    }
}