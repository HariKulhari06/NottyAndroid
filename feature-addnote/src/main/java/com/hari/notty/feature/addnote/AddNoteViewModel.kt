package com.hari.notty.feature.addnote

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor() : ViewModel() {
    private val _headline = MutableStateFlow("Getting started with Android Jetpack.")
    val headline: StateFlow<String> = _headline

    private val _note = MutableStateFlow("Jetpack encompasses a collection of Android libraries that incorporate best practices and provide backwards compatibility in your Android apps.\n" +
            "\n" +
            "The Jetpack guide to app architecture provides an overview of the best practices and recommended architecture to consider as you build your Android app.\n" +
            "\n" +
            "The following sections cover how you can get started using Jetpack components.")
    val note: StateFlow<String> = _note


    fun setHeadline(headline:String){
        _headline.value = headline
    }

    fun setNote(headline:String){
        _note.value = headline
    }
}