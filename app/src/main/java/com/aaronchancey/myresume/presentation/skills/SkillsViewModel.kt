package com.aaronchancey.myresume.presentation.skills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronchancey.myresume.data.ResumeRepository
import com.aaronchancey.myresume.domain.mappers.toSkillGroup
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
class SkillsViewModel @Inject constructor(
    private val resumeRepository: ResumeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SkillsState())
    val state = combine(
        _state,
        getSkills(),
    ) { state, skills ->
        state.copy(skills = skills)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SkillsState(),
        )

    private fun getSkills() = flow {
        _state.update { it.copy(loading = true) }
        resumeRepository.getSkills()
            .onSuccess { skills ->
                emit(skills.map { it.toSkillGroup() })
                _state.update { it.copy(loading = false) }
            }
            .onError { dataError, data ->
                println("dataError: $dataError")
                emit(emptyList())
                _state.update { it.copy(loading = false) }
            }
    }
}
