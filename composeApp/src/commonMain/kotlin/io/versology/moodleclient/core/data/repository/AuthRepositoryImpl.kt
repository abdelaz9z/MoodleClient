package io.versology.moodleclient.core.data.repository

import io.versology.moodleclient.core.data.SessionManager
import io.versology.moodleclient.core.data.mapper.toDomain
import io.versology.moodleclient.core.data.network.MoodleApi
import io.versology.moodleclient.core.domain.model.SiteInfo
import io.versology.moodleclient.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.StateFlow

class AuthRepositoryImpl(
    private val api: MoodleApi,
    private val sessionManager: SessionManager,
) : AuthRepository {

    override val isLoggedIn: StateFlow<Boolean>
        get() = sessionManager.isLoggedIn

    override val userFirstName: String
        get() = sessionManager.userFirstName

    override suspend fun loginWithCredentials(username: String, password: String): Result<SiteInfo> {
        return runCatching {
            val response = api.login(username, password)

            if (response.error != null) {
                throw Exception(response.error)
            }

            val token = response.token
                ?: throw Exception("No token received from server")

            sessionManager.loginWithToken(token)

            val siteInfoDto = api.getSiteInfo()
            val siteInfo = siteInfoDto.toDomain()
            sessionManager.updateProfile(siteInfo)

            siteInfo
        }
    }

    override suspend fun loginWithToken(token: String): Result<SiteInfo> {
        return runCatching {
            sessionManager.loginWithToken(token)

            val siteInfoDto = api.getSiteInfo()
            val siteInfo = siteInfoDto.toDomain()
            sessionManager.updateProfile(siteInfo)

            siteInfo
        }
    }

    override fun logout() {
        sessionManager.logout()
    }
}
