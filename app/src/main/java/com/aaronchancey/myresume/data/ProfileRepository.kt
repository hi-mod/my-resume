package com.aaronchancey.myresume.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getProfile() = safeCall<String> {
        httpClient.get(urlString = HttpRoutes.PROFILE) {
            header("x-api-key", "AIzaSyBYJC5Ha8Q_Cfr2DLs-2j7hiXPIVtc2RM8")
        }
    }
}
