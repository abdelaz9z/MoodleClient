package io.versology.moodleclient.core.domain.usecase

import io.versology.moodleclient.core.domain.model.CourseSection
import io.versology.moodleclient.core.domain.repository.CourseRepository

class GetCourseContentsUseCase(
    private val repository: CourseRepository,
) {
    suspend operator fun invoke(courseId: Int): Result<List<CourseSection>> =
        repository.getCourseContents(courseId)
}
