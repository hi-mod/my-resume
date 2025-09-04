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
    // To support ETag caching:
    // 1. Add an 'etag: String?' field to your ProfileEntity and update your DAO/mappers accordingly.
    // 2. When fetching from cache, also retrieve the stored ETag value.
    // 3. When refreshing from server, send the ETag in the 'If-None-Match' header.
    // 4. If the server responds with 304 Not Modified, keep the cached value.
    // 5. If the server responds with new data, update both the profile and the ETag in the database.

    suspend fun getProfile(): Result<Profile, DataError> = withContext(Dispatchers.IO) {
        // Example: Retrieve cached profile and ETag
        // val cached = resumeDatabase.profileDao().getAll().firstOrNull() // Also get cached.etag
        resumeDatabase.profileDao().getAll().firstOrNull()?.let { cached ->
            // You could launch a background refresh here, sending cached.etag in 'If-None-Match'
            // If server returns 304, do nothing. If server returns new data, update cache and ETag.
            return@withContext Result.Success(cached.toProfile())
        }

        // When no cache, fetch from server as usual
        val result = safeCall<ProfileResponse> {
            httpClient.get(urlString = HttpRoutes.PROFILE) {
                header("x-api-key", BuildConfig.API_KEY)
                // If you have a cached ETag, add: header("If-None-Match", cached.etag)
            }
        }.onSuccess { profileResponse ->
            // When updating cache, also store the new ETag from response.headers["ETag"]
            resumeDatabase.profileDao().insertAll(profileResponse.toProfileEntity())
        }

        when (result) {
            is Result.Success -> Result.Success(result.data.toProfile())
            is Result.Error -> Result.Error(result.error)
        }
    }

    suspend fun getExperience(): Result<List<ExperienceResponse>, DataError> = withContext(Dispatchers.IO) {
        safeCall<List<ExperienceResponse>> {
            httpClient.get(urlString = HttpRoutes.EXPERIENCE) {
                header("x-api-key", BuildConfig.API_KEY)
            }
        }
    }

    suspend fun getReferences(): Result<List<ReferencesResponse>, DataError> = withContext(Dispatchers.IO) {
        safeCall<List<ReferencesResponse>> {
            httpClient.get(urlString = HttpRoutes.REFERENCES) {
                header("x-api-key", BuildConfig.API_KEY)
            }
        }
    }

    suspend fun getSkills(): Result<List<SkillsResponse>, DataError> = withContext(Dispatchers.IO) {
        safeCall<List<SkillsResponse>> {
            httpClient.get(urlString = HttpRoutes.SKILLS) {
                header("x-api-key", BuildConfig.API_KEY)
            }
        }
    }
}
