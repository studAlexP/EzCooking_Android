package com.example.ezcooking.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezcooking.models.Keyword
import com.example.ezcooking.repository.ScaleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ScaleViewModel : ViewModel() {

    val keywords = MutableStateFlow<Keyword?>(null)

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                ScaleRepository.getKeywords()
            }.onSuccess {
                keywords.value = it
            }.onFailure {
                keywords.value = null
            }
        }
    }
}