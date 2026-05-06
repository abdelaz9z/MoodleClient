package io.versology.moodleclient.core.domain.model

data class SiteInfo(
    val userId: Int,
    val fullName: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val pictureUrl: String,
    val siteName: String,
    val siteUrl: String,
)
