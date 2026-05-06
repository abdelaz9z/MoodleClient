package io.versology.moodleclient.core.data

import io.versology.moodleclient.core.domain.model.SiteInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Manages the user session: stores token, user ID, and profile info.
 */
class SessionManager {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    var token: String = ""
        private set

    var userId: Int = 0
        private set

    var userFullName: String = ""
        private set

    var userFirstName: String = ""
        private set

    var userPictureUrl: String = ""
        private set

    var siteName: String = ""
        private set

    fun loginWithToken(token: String) {
        this.token = token
        _isLoggedIn.value = true
    }

    fun updateProfile(siteInfo: SiteInfo) {
        userId = siteInfo.userId
        userFullName = siteInfo.fullName
        userFirstName = siteInfo.firstName
        userPictureUrl = siteInfo.pictureUrl
        siteName = siteInfo.siteName
    }

    fun logout() {
        token = ""
        userId = 0
        userFullName = ""
        userFirstName = ""
        userPictureUrl = ""
        siteName = ""
        _isLoggedIn.value = false
    }
}
