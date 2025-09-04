package com.aaronchancey.myresume.data

import com.aaronchancey.myresume.BuildConfig
import com.aaronchancey.myresume.database.ResumeDatabase
import com.aaronchancey.myresume.domain.DataError
import com.aaronchancey.myresume.domain.Profile
import com.aaronchancey.myresume.domain.Result
import com.aaronchancey.myresume.domain.mappers.toProfile
import com.aaronchancey.myresume.domain.mappers.toProfileEntity
import com.aaronchancey.myresume.domain.onSuccess
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResumeRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val resumeDatabase: ResumeDatabase,
) {
    suspend fun getProfile(): Result<Profile, DataError> = withContext(Dispatchers.IO) {
        resumeDatabase.profileDao().getAll().let { cachedProfile ->
            if (cachedProfile.isNotEmpty()) {
                Result.Success(cachedProfile.first().toProfile())
            } else {
                val result = safeCall<ProfileResponse> {
                    httpClient.get(urlString = HttpRoutes.PROFILE) {
                        header("x-api-key", BuildConfig.API_KEY)
                    }
                }
                    .onSuccess { profileResponse ->
                        resumeDatabase.profileDao().insertAll(profileResponse.toProfileEntity())
                    }
                when (result) {
                    is Result.Error -> Result.Error(result.error)
                    is Result.Success -> Result.Success(result.data.toProfile())
                }
            }
        }
    }

    suspend fun getExperience() = safeCall<List<ExperienceResponse>> {
        httpClient.get(urlString = HttpRoutes.EXPERIENCE) {
            header("x-api-key", BuildConfig.API_KEY)
        }
    }

    suspend fun getReferences() = safeCall<List<ReferencesResponse>> {
        httpClient.get(urlString = HttpRoutes.REFERENCES) {
            header("x-api-key", BuildConfig.API_KEY)
        }
    }

    suspend fun getSkills() = safeCall<List<SkillsResponse>> {
        httpClient.get(urlString = HttpRoutes.SKILLS) {
            header("x-api-key", BuildConfig.API_KEY)
        }
    }
}
