package com.aaronchancey.myresume.presentation.skills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronchancey.myresume.database.SkillsRepository
import com.aaronchancey.myresume.domain.mappers.toSkillGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class SkillsViewModel @Inject constructor(
    val skillsRepository: SkillsRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SkillsState())
    val state = combine(_state, skillsRepository.getSkills()) { state, skills ->
        state.copy(skills = skills.map { skill -> skill.toSkillGroup() })
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SkillsState(),
        )
}
