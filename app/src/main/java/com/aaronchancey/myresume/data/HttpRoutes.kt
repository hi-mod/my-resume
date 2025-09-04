package com.aaronchancey.myresume.data

data object HttpRoutes {
    private const val BASE_URL = "https://my-gateway-2uhsha19.ue.gateway.dev/v1"
    const val PROFILE = "$BASE_URL/profile"
    const val EXPERIENCE = "$BASE_URL/experience"
    const val SKILLS = "$BASE_URL/skills"
    const val REFERENCES = "$BASE_URL/references"
}
