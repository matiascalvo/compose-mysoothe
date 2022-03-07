package com.matias.mysoothe.domain.usecase

import com.matias.mysoothe.domain.model.Record
import com.matias.mysoothe.domain.repositories.RecordsRepository
import javax.inject.Inject

class GetRecordsUseCase @Inject constructor(private val repo: RecordsRepository) {
    suspend operator fun invoke(filter: String): Result<List<Record>> {
        return Result.success(repo.getRecords(filter))
    }
}
