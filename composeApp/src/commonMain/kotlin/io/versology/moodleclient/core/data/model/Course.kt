package io.versology.moodleclient.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    val id: Int,
    val shortname: String = "",
    val fullname: String = "",
    val displayname: String = "",
    val courseimage: String = "",
    val progress: Float? = null,
    val completed: Boolean = false,
    val summary: String = "",
    val enrolledusercount: Int = 0,
    val startdate: Long = 0,
    val enddate: Long = 0,
    val visible: Int = 1,
    val enablecompletion: Boolean = false,
)
