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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class ReferencesViewModel @Inject constructor(
    private val resumeRepository: ResumeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ReferencesState())
    val state = combine(
        _state,
        getReferences(),
    ) { state, references ->
        state.copy(references = references)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ReferencesState(),
        )

    private fun getReferences() = flow {
        _state.update { it.copy(loading = true) }
        resumeRepository.getReferences()
            .onSuccess { references ->
                emit(references.map { it.toReference() })
                _state.update { it.copy(loading = false) }
            }
            .onError { dataError, data ->
                println("dataError: $dataError")
                emit(emptyList())
                _state.update { it.copy(loading = false) }
            }
    }
}
