package com.aaronchancey.myresume.presentation.developerprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronchancey.myresume.data.ResumeRepository
import com.aaronchancey.myresume.domain.Profile
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
class ProfileViewModel @Inject constructor(
    private val profileRepository: ResumeRepository,
) : ViewModel() {
    private val _profileState = MutableStateFlow(ProfileState())
    val profileState = _profileState
        .onStart { getProfile() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ProfileState(),
        )

    private fun getProfile() {
        _profileState.update { it.copy(loading = true) }
        viewModelScope.launch {
            profileRepository.getProfile()
                .onSuccess { profile ->
                    _profileState.update { it.copy(loading = false, profile = profile) }
                }
                .onError { dataError, data ->
                    _profileState.update { it.copy(loading = false, profile = Profile(profileText = "")) }
                }
        }
    }
}
