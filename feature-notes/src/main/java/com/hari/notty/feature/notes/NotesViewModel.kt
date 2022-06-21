package com.hari.notty.feature.notes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.notty.core.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count = _count.asSharedFlow()

    init {
        addNote()
    }


    fun increment() {
        viewModelScope.launch {
            _count.value = _count.value.plus(1)
        }
    }

    fun addNote() {
        viewModelScope.launch {
            noteRepository.addNote()
        }
    }
}