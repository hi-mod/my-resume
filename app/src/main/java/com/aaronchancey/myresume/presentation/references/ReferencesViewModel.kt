package com.aaronchancey.myresume.presentation.references

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronchancey.myresume.database.ReferenceRepository
import com.aaronchancey.myresume.domain.mappers.toReference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ReferencesViewModel @Inject constructor(
    referenceRepository: ReferenceRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ReferencesState())
    val state = combine(_state, referenceRepository.getReferences()) { state, references ->
        state.copy(references = references.map { it.toReference() })
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ReferencesState(),
        )
}
