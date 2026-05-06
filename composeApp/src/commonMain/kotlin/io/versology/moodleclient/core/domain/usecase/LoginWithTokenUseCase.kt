package io.versology.moodleclient.core.domain.usecase

import io.versology.moodleclient.core.domain.model.SiteInfo
import io.versology.moodleclient.core.domain.repository.AuthRepository

class LoginWithTokenUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(token: String): Result<SiteInfo> =
        repository.loginWithToken(token)
}
