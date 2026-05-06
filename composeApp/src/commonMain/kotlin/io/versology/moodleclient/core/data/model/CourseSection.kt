package io.versology.moodleclient.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseSectionDto(
    val id: Int,
    val name: String = "",
    val visible: Int = 1,
    val summary: String = "",
    val section: Int = 0,
    val modules: List<CourseModuleDto> = emptyList(),
)

@Serializable
data class CourseModuleDto(
    val id: Int,
    val name: String = "",
    val modname: String = "",
    val modicon: String = "",
    val url: String? = null,
    val modplural: String = "",
    val purpose: String = "",
)
