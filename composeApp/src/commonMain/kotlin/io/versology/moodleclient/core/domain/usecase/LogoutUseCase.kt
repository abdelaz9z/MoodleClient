package io.versology.moodleclient.core.domain.usecase

import io.versology.moodleclient.core.domain.repository.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository,
) {
    operator fun invoke() = repository.logout()
}
