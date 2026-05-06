package io.versology.moodleclient.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String? = null,
    val privatetoken: String? = null,
    val error: String? = null,
    val errorcode: String? = null,
)

@Serializable
data class SiteInfoDto(
    val sitename: String = "",
    val username: String = "",
    val firstname: String = "",
    val lastname: String = "",
    val fullname: String = "",
    val userid: Int = 0,
    val siteurl: String = "",
    val userpictureurl: String = "",
    val lang: String = "",
    val release: String = "",
)
