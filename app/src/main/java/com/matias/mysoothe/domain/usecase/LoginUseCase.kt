package com.matias.mysoothe.domain.usecase

import com.matias.mysoothe.domain.repositories.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return userRepository.login(email = email.trim(), password = password.trim())
    }
}
