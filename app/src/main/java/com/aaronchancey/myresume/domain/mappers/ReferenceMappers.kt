package com.aaronchancey.myresume.domain.mappers

import com.aaronchancey.myresume.database.model.ReferenceEntity
import com.aaronchancey.myresume.domain.Reference

fun ReferenceEntity.toReference() = Reference(
    name = name,
    phoneNumber = phoneNumber,
    email = email,
)
