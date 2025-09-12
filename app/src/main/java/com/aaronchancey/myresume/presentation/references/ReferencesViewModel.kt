package com.aaronchancey.myresume.presentation.references

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronchancey.myresume.data.ResumeRepository
import com.aaronchancey.myresume.domain.mappers.toReference
import com.aaronchancey.myresume.domain.onError
import com.aaronchancey.myresume.domain.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ReferencesViewModel @Inject constructor(
    private val resumeRepository: ResumeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ReferencesState())
    val state = _state
        .onStart { getReferences() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ReferencesState(),
        )

    private fun getReferences() {
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            resumeRepository.getReferences()
                .onSuccess { references ->
                    _state.update { state -> state.copy(loading = false, references = references.map { it.toReference() }) }
                }
                .onError { dataError, data ->
                    _state.update { state -> state.copy(loading = false, references = emptyList()) }
                }
        }
    }
}
