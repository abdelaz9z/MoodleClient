package io.versology.moodleclient.core.domain.model

data class Course(
    val id: Int,
    val shortName: String,
    val fullName: String,
    val imageUrl: String,
    val progress: Float?,
    val isCompleted: Boolean,
    val summary: String,
    val enrolledUserCount: Int,
)
