package io.versology.moodleclient.core.domain.model

data class GradeItem(
    val id: Int,
    val name: String?,
    val itemType: String,
    val module: String?,
    val gradeRaw: Float?,
    val gradeFormatted: String,
    val gradeMin: Float,
    val gradeMax: Float,
    val rangeFormatted: String,
    val percentageFormatted: String,
    val isHidden: Boolean,
)
