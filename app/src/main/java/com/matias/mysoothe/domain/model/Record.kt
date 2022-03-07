package com.matias.mysoothe.domain.model

enum class RecordType { FAVORITE, MIND, BODY }
data class Record(
    val name: String,
    val imageRes: Int,
    val type: RecordType
)
