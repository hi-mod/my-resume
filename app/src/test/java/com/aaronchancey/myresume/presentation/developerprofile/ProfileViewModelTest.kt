package com.aaronchancey.myresume.presentation.developerprofile

import com.aaronchancey.myresume.data.ResumeRepository
import com.aaronchancey.myresume.domain.DataError
import com.aaronchancey.myresume.domain.Profile
import com.aaronchancey.myresume.domain.Result
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var repository: ResumeRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = ProfileViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getProfile success updates state with profile`() = runTest {
        // Given
        val profile = Profile("This is my profile.")
        coEvery { repository.getProfile() } returns Result.Success(profile)

        // When

        backgroundScope.launch {
            viewModel.profileState.collect {
                println(it.profile)
            }
        }
        val states = viewModel.profileState.take(2).toList()

        // Then
        states.size shouldBe 2
        states[0].profile shouldBe Profile("")
        states[0].loading shouldBe true
        states[1].profile shouldBe profile
        states[1].loading shouldBe false
    }

    @Test
    fun `getProfile error updates state with empty profile`() = runTest {
        // Given
        coEvery { repository.getProfile() } returns Result.Error(DataError.Remote.BAD_REQUEST, "")

        // When
        val states = viewModel.profileState.take(2).toList()

        // Then
        states.size shouldBe 2
        states[0].profile shouldBe Profile("")
        states[0].loading shouldBe true
        states[1].profile shouldBe Profile("")
        states[1].loading shouldBe false
    }
}
