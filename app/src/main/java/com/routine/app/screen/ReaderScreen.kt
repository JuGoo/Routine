package com.routine.app.screen

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString

@Composable
internal fun ReaderScreen(
    modifier: Modifier = Modifier,
    annotatedString: AnnotatedString
) {
    Text(modifier = modifier.verticalScroll(rememberScrollState()), text = annotatedString)
}