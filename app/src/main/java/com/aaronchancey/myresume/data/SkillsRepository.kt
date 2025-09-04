package com.aaronchancey.myresume.database

import com.aaronchancey.myresume.database.model.SkillGroupEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

class SkillsRepository @Inject constructor() {
    fun getSkills() = flowOf(
        listOf(
            SkillGroupEntity(title = "Languages", skills = listOf("Kotlin", "Java", "Swift", "Objective-C", "C#", "TypeScript", "JavaScript")),
            SkillGroupEntity(title = "Architectures", skills = listOf("MVC", "MVI", "MVP", "MVVM")),
            SkillGroupEntity(title = "UI Development", skills = listOf("Jetpack Compose (Android)", "Compose Multiplatform (CMP)", "Android Views & XML layouts (legacy support)", "Theming & Material Design 3", "Accessibility & Inclusive Design")),
            SkillGroupEntity(title = "Multiplatform Development", skills = listOf("Kotlin Multiplatform (KMP)", "Shared business logic across Android/iOS", "expect/actual pattern", "Swift/Objective-C interop for iOS")),
            SkillGroupEntity(title = "Data & Persistence", skills = listOf("Room Database (local persistence)", "SQLDelight (KMP persistence)", "DataStore (preferences & proto)", "Caching & offline-first strategies")),
            SkillGroupEntity(title = "Networking", skills = listOf("Retrofit / OkHttp3 (Android)", "Ktor Client/Server (multiplatform networking)", "WebSockets")),
            SkillGroupEntity(title = "Dependency Injection & Modularity", skills = listOf("Hilt / Dagger", "Koin (lightweight DI)", "Gradle (Groovy & Kotlin DSL)", "Modularization & sourceSets (multi-variant builds)")),
            SkillGroupEntity(title = "Testing & Quality", skills = listOf("JUnit, MockK, Espresso, Compose UI testing", "CI/CD integration (GitHub Actions, Jenkins)", "Code quality tools (Ktlint)")),
        ),
    )
}
