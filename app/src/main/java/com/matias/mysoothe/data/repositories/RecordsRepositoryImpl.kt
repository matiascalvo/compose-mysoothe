package com.matias.mysoothe.data.repositories

import com.matias.mysoothe.domain.model.Record
import com.matias.mysoothe.domain.repositories.RecordsRepository

class RecordsRepositoryImpl : RecordsRepository {
    override suspend fun getRecords(filter: String): List<Record> {
        return records.filter {
            it.name.contains(other = filter, ignoreCase = true)
        }
    }
}
