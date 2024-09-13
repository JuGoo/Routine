package com.routine.presentation.edit.model

sealed class TextData {
    abstract val text: String

    data class PlainTextData(override val text: String) : TextData()
    data class LinkTextData(
        override val text: String, val start: Int,
        val end: Int
    ) : TextData()

}