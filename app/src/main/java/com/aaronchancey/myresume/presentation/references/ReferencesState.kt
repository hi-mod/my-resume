package com.aaronchancey.myresume.presentation.references

import com.aaronchancey.myresume.domain.Reference

data class ReferencesState(
    val loading: Boolean = true,
    val references: List<Reference> = emptyList(),
)
