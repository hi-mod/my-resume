package com.aaronchancey.myresume.presentation.developerprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronchancey.myresume.data.ResumeRepository
import com.aaronchancey.myresume.data.onError
import com.aaronchancey.myresume.data.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ResumeRepository,
) : ViewModel() {
    private val _profileState = MutableStateFlow(ProfileState())
    val profileState = combine(_profileState, getProfile()) { state, profile ->
        state.copy(
            profile = profile,
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ProfileState(),
        )

    private fun getProfile() = flow {
        _profileState.update { it.copy(loading = true) }
        profileRepository.getProfile()
            .onSuccess { profile ->
                emit(profile)
                _profileState.update { it.copy(loading = false) }
            }
            .onError { dataError, data ->
                emit("")
                _profileState.update { it.copy(loading = false) }
            }
    }
}
