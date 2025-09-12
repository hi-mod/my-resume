package com.aaronchancey.myresume.presentation.experience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronchancey.myresume.data.ResumeRepository
import com.aaronchancey.myresume.domain.mappers.toExperienceEntry
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
class ExperienceViewModel @Inject constructor(
    private val resumeRepository: ResumeRepository,
) : ViewModel() {
    private val _experienceState = MutableStateFlow(ExperienceState())
    val experienceState = _experienceState
        .onStart { getExperience() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ExperienceState(),
        )

    private fun getExperience() {
        _experienceState.update { it.copy(loading = true) }
        viewModelScope.launch {
            resumeRepository.getExperience()
                .onSuccess { experience ->
                    _experienceState.update { state -> state.copy(experienceEntries = experience.map { it.toExperienceEntry() }, loading = false) }
                }
                .onError { dataError, data ->
                    _experienceState.update { state -> state.copy(experienceEntries = emptyList(), loading = false) }
                }
        }
    }
}
