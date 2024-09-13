package com.routine.presentation.di

import com.routine.presentation.edit.EditViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val presentationModule = module {
    viewModelOf(::EditViewModel)
}