package io.versology.moodleclient.core.domain.repository

import io.versology.moodleclient.core.domain.model.SiteInfo
import kotlinx.coroutines.flow.StateFlow

/**
 * Authentication operations contract.
 * Single Responsibility: handles only login, logout, and session state.
 */
interface AuthRepository {

    val isLoggedIn: StateFlow<Boolean>

    val userFirstName: String

    suspend fun loginWithCredentials(username: String, password: String): Result<SiteInfo>

    suspend fun loginWithToken(token: String): Result<SiteInfo>

    fun logout()
}
