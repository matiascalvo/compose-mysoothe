package com.matias.mysoothe.data.repositories

import com.matias.mysoothe.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (users.containsKey(email) && users[email] == password) {
            Result.success(Unit)
        } else {
            Result.failure(UserRepository.UnableToLoginException())
        }
    }
}

private val users = mapOf(
    "test@test.com" to "test",
    "your@email.com" to "your",
    "a@a.com" to "aaaa"
)
