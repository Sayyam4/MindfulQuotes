package com.sayyam.mindfulquotes.presentation.top_app_bar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sayyam.mindfulquotes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesTopAppBar (modifier: Modifier = Modifier) {

    CenterAlignedTopAppBar (
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier
    )
}