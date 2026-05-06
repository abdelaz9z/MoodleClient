package io.versology.moodleclient.core.data.mapper

import io.versology.moodleclient.core.data.model.CourseModuleDto
import io.versology.moodleclient.core.data.model.CourseSectionDto
import io.versology.moodleclient.core.domain.model.CourseModule
import io.versology.moodleclient.core.domain.model.CourseSection

fun CourseSectionDto.toDomain(): CourseSection = CourseSection(
    id = id,
    name = name,
    sectionIndex = section,
    summary = summary,
    modules = modules.map { it.toDomain() },
)

fun CourseModuleDto.toDomain(): CourseModule = CourseModule(
    id = id,
    name = name,
    type = modname,
    iconUrl = modicon,
    url = url,
    typePlural = modplural,
    purpose = purpose,
)

fun List<CourseSectionDto>.toDomain(): List<CourseSection> = map { it.toDomain() }
