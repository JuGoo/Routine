package com.routine.app.screen.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.routine.app.R
import com.routine.app.screen.ReaderScreen
import com.routine.presentation.edit.EditAction
import com.routine.presentation.edit.EditViewModel
import com.routine.presentation.edit.model.Mode
import com.routine.presentation.edit.model.TextData
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun EditScreenRoute(
    editViewModel: EditViewModel = koinViewModel()
) {
    val uiState by editViewModel.uiState.collectAsStateWithLifecycle()
    var text: String by remember {
        mutableStateOf(uiState.content.joinToString("") { it.text })
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(modifier = Modifier.weight(1f), text = uiState.title)
                val painter = when (uiState.mode) {
                    Mode.Read -> painterResource(id = R.drawable.ic_edit)
                    Mode.Edit -> painterResource(id = R.drawable.ic_validate)
                }
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            editViewModel.handleAction(EditAction.SwitchMode(text))
                        }, painter = painter, contentDescription = ""
                )
            }
        },
        content = {
            when (uiState.mode) {
                Mode.Read -> ReaderScreen(
                    modifier = Modifier.padding(it),
                    annotatedString = createAnnotatedString(uiState.content)
                )

                Mode.Edit -> EditScreen(
                    modifier = Modifier.padding(it),
                    onTextChanged = { value ->
                        text = value
                    },
                    text = text
                )
            }
        }
    )
}

@Composable
private fun createAnnotatedString(content: List<TextData>) =
    buildAnnotatedString {
        content.forEach { textData ->
            when (textData) {
                is TextData.PlainTextData -> append(textData.text)
                is TextData.LinkTextData -> withLink(
                    link = LinkAnnotation.Url(
                        url = textData.text,
                        styles = TextLinkStyles(style = SpanStyle(color = Color.Blue))
                    )
                ) {
                    append(textData.text)
                }
            }
        }
    }
