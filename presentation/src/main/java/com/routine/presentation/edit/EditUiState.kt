package com.routine.presentation.edit

import com.routine.presentation.edit.model.Mode
import com.routine.presentation.edit.model.TextData

data class EditUiState(
    val title: String = "",
    val description: String = "",
    val isSaveEnabled: Boolean = false,
    val mode: Mode = Mode.Read,
    val content: List<TextData> = listOf(
        TextData.PlainTextData("Hello World \n This is a test \n")
    )
)