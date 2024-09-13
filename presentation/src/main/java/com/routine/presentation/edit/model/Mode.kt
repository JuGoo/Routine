package com.routine.presentation.edit.model

sealed class Mode {
    data object Read : Mode()
    data object Edit : Mode()
}