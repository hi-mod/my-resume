package com.aaronchancey.myresume.data

import com.aaronchancey.myresume.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import javax.inject.Inject

class ResumeRepository @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getProfile() = safeCall<String> {
        httpClient.get(urlString = HttpRoutes.PROFILE) {
            header("x-api-key", BuildConfig.API_KEY)
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
