package io.versology.moodleclient.core.domain.usecase

import io.versology.moodleclient.core.domain.model.SiteInfo
import io.versology.moodleclient.core.domain.repository.AuthRepository

class LoginWithCredentialsUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(username: String, password: String): Result<SiteInfo> =
        repository.loginWithCredentials(username, password)
}
