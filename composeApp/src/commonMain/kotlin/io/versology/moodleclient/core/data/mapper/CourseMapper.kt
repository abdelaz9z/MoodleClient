package io.versology.moodleclient.core.data.mapper

import io.versology.moodleclient.core.data.model.CourseDto
import io.versology.moodleclient.core.domain.model.Course

fun CourseDto.toDomain(): Course = Course(
    id = id,
    shortName = shortname,
    fullName = fullname,
    imageUrl = courseimage,
    progress = progress,
    isCompleted = completed,
    summary = summary,
    enrolledUserCount = enrolledusercount,
)

fun List<CourseDto>.toDomain(): List<Course> = map { it.toDomain() }
