package io.versology.moodleclient.core.domain.model

data class CourseSection(
    val id: Int,
    val name: String,
    val sectionIndex: Int,
    val summary: String,
    val modules: List<CourseModule>,
)

data class CourseModule(
    val id: Int,
    val name: String,
    val type: String,
    val iconUrl: String,
    val url: String?,
    val typePlural: String,
    val purpose: String,
)
