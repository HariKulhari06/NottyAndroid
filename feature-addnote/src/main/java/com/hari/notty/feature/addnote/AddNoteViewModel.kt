package com.hari.notty.feature.addnote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.notty.core.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _title = MutableStateFlow("Getting started with Android Jetpack.")
    val title: StateFlow<String> = _title

    private val _description = MutableStateFlow(
        "Jetpack encompasses a collection of Android libraries that incorporate best practices and provide backwards compatibility in your Android apps.\n" +
                "\n" +
                "The Jetpack guide to app architecture provides an overview of the best practices and recommended architecture to consider as you build your Android app.\n" +
                "\n" +
                "The following sections cover how you can get started using Jetpack components."
    )
    val description: StateFlow<String> = _description


    fun setHeadline(headline: String) {
        _title.value = headline
    }

    fun setNote(headline: String) {
        _description.value = headline
    }


    fun addNote() {
        viewModelScope.launch {
            noteRepository.addNote(title.value, description.value)
        }
    }
}