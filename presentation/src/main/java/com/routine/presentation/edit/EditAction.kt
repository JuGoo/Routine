package com.routine.presentation.edit

sealed class EditAction {
    data class SwitchMode(val content: String) : EditAction()
}