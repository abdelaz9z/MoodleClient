package io.versology.moodleclient.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GradeReportResponseDto(
    val usergrades: List<UserGradeDto> = emptyList(),
    val warnings: List<GradeWarningDto> = emptyList(),
)

@Serializable
data class UserGradeDto(
    val courseid: Int,
    val userid: Int,
    val userfullname: String = "",
    val maxdepth: Int = 0,
    val gradeitems: List<GradeItemDto> = emptyList(),
)

@Serializable
data class GradeItemDto(
    val id: Int,
    val itemname: String? = null,
    val itemtype: String = "",
    val itemmodule: String? = null,
    val iteminstance: Int? = null,
    val graderaw: Float? = null,
    val gradeformatted: String = "-",
    val grademin: Float = 0f,
    val grademax: Float = 100f,
    val rangeformatted: String = "",
    val percentageformatted: String = "-",
    val feedback: String = "",
    val cmid: Int? = null,
    val gradehiddenbydate: Boolean = false,
    val gradeneedsupdate: Boolean = false,
    val gradeishidden: Boolean = false,
)

@Serializable
data class GradeWarningDto(
    val item: String = "",
    val itemid: Int = 0,
    val warningcode: String = "",
    val message: String = "",
)
