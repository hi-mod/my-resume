package com.aaronchancey.myresume.domain.mappers

import com.aaronchancey.myresume.data.ReferencesResponse
import com.aaronchancey.myresume.database.model.ReferenceEntity
import com.aaronchancey.myresume.domain.Reference

fun ReferenceEntity.toReference() = Reference(
    name = name,
    phoneNumber = phoneNumber,
    email = email,
)

fun ReferencesResponse.toReference() = Reference(
    name = name,
    phoneNumber = phoneNumber,
    email = email,
)
