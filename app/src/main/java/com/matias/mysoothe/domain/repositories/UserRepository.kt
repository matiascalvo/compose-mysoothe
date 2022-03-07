package com.matias.mysoothe.domain.repositories

interface UserRepository {
    suspend fun login(email: String, password: String): Result<Unit>

    class UnableToLoginException : RuntimeException()
}
