package com.aaronchancey.myresume.database

import com.aaronchancey.myresume.database.model.ReferenceEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

class ReferenceRepository @Inject constructor() {
    fun getReferences() = flowOf(
        listOf(
            ReferenceEntity(
                name = "Scott Lukse",
                phoneNumber = "1234567890",
                email = "test@test.com",
            ),
            ReferenceEntity(
                name = "Jeff Breece",
                phoneNumber = "1234567890",
                email = "test@test.com",
            ),
            ReferenceEntity(
                name = "Brett Berliner",
                phoneNumber = "1234567890",
                email = "test@test.com",
            ),
        ),
    )
}
