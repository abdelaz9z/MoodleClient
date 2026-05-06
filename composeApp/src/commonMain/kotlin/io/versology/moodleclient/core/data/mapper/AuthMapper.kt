package io.versology.moodleclient.core.data.mapper

import io.versology.moodleclient.core.data.model.SiteInfoDto
import io.versology.moodleclient.core.domain.model.SiteInfo

fun SiteInfoDto.toDomain(): SiteInfo = SiteInfo(
    userId = userid,
    fullName = fullname,
    firstName = firstname,
    lastName = lastname,
    username = username,
    pictureUrl = userpictureurl,
    siteName = sitename,
    siteUrl = siteurl,
)
