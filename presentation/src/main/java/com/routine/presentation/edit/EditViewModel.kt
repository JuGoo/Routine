package com.routine.presentation.edit

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routine.presentation.edit.model.Mode
import com.routine.presentation.edit.model.TextData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class EditViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<EditUiState> = MutableStateFlow(EditUiState())
    val uiState: StateFlow<EditUiState> = _uiState.asStateFlow()

    fun handleAction(editAction: EditAction) {
        viewModelScope.launch {
            when (editAction) {
                is EditAction.SwitchMode -> handleSwitchMode(editAction)
            }
        }
    }

    private fun handleSwitchMode(action: EditAction.SwitchMode) {
        if (_uiState.value.mode is Mode.Read) {
            _uiState.value = _uiState.value.copy(mode = Mode.Edit)
        } else {
            _uiState.value = _uiState.value.copy(
                mode = Mode.Read,
                content = computeContent(action.content)
            )
        }
    }
}

private fun computeContent(source: String): List<TextData> {
    val content = getLinks(source)
    val urls = content.map { (start, end) ->
        TextData.LinkTextData(source.substring(start, end), start, end)
    }
    val plainText = content.mapIndexed { index, (start) ->
        if (index == 0) {
            TextData.PlainTextData(source.substring(0, start))
        } else {
            TextData.PlainTextData(source.substring(content[index - 1].second, start))
        }
    }
    return plainText.zip(urls).flatMap { (content, url) -> listOf(content, url) }
}

private fun getLinks(source: String): List<Pair<Int, Int>> {
    val urlPattern: Pattern = Patterns.WEB_URL
    val urlList = mutableListOf<Pair<Int, Int>>()
    val urlMatcher = urlPattern.matcher(source)
    var matchStart: Int
    var matchEnd: Int
    while (urlMatcher.find()) {
        matchStart = urlMatcher.start(1)
        matchEnd = urlMatcher.end()
        urlList.add(Pair(matchStart, matchEnd))
    }
    return urlList
}