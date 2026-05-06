package io.versology.moodleclient.core.data.mapper

import io.versology.moodleclient.core.data.model.GradeItemDto
import io.versology.moodleclient.core.data.model.GradeReportResponseDto
import io.versology.moodleclient.core.domain.model.GradeItem

fun GradeItemDto.toDomain(): GradeItem = GradeItem(
    id = id,
    name = itemname,
    itemType = itemtype,
    module = itemmodule,
    gradeRaw = graderaw,
    gradeFormatted = gradeformatted,
    gradeMin = grademin,
    gradeMax = grademax,
    rangeFormatted = rangeformatted,
    percentageFormatted = percentageformatted,
    isHidden = gradeishidden,
)

/**
 * Extracts all grade items from the first user grade in the response.
 */
fun GradeReportResponseDto.toGradeItems(): List<GradeItem> =
    usergrades.firstOrNull()?.gradeitems?.map { it.toDomain() } ?: emptyList()
