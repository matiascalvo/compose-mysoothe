package com.matias.mysoothe.domain.repositories

import com.matias.mysoothe.domain.model.Record

interface RecordsRepository {
    suspend fun getRecords(filter: String): List<Record>
}
