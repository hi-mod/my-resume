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
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SkillsViewModel @Inject constructor(
    private val resumeRepository: ResumeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SkillsState())
    val state = _state
        .onStart { getSkills() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SkillsState(),
        )

    private fun getSkills() {
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            resumeRepository.getSkills()
                .onSuccess { skills ->
                    _state.update { state -> state.copy(loading = false, skills = skills.map { it.toSkillGroup() }) }
                }
                .onError { dataError, data ->
                    _state.update { it.copy(loading = false, skills = emptyList()) }
                }
        }
    }
}
