package com.aaronchancey.myresume.presentation.experience

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronchancey.myresume.database.ExperienceEntryRepository
import com.aaronchancey.myresume.domain.mappers.toExperienceEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ExperienceViewModel @Inject constructor(
    experienceEntryRepository: ExperienceEntryRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _experienceState = MutableStateFlow(ExperienceState())
    val experienceState = _experienceState.map { state ->
        val experienceEntries = experienceEntryRepository.getExperienceEntries()
        println("Experience entries: $experienceEntries")
        state.copy(experienceEntries = experienceEntries.map { it.toExperienceEntry() })
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ExperienceState(),
        )
}
