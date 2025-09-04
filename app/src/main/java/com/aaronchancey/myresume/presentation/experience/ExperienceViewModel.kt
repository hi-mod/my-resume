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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class ExperienceViewModel @Inject constructor(
    private val resumeRepository: ResumeRepository,
) : ViewModel() {
    private val _experienceState = MutableStateFlow(ExperienceState())
    val experienceState = combine(
        _experienceState,
        getExperience(),
    ) { state, experience ->
        state.copy(experienceEntries = experience)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ExperienceState(),
        )

    private fun getExperience() = flow {
        _experienceState.update { it.copy(loading = true) }
        resumeRepository.getExperience()
            .onSuccess { experience ->
                emit(experience.map { it.toExperienceEntry() })
                _experienceState.update { it.copy(loading = false) }
            }
            .onError { dataError, data ->
                emit(emptyList())
                _experienceState.update { it.copy(loading = false) }
            }
    }
}
