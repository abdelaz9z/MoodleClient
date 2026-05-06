package io.versology.moodleclient.core.domain.usecase

import io.versology.moodleclient.core.domain.model.Course
import io.versology.moodleclient.core.domain.repository.CourseRepository

class GetCoursesUseCase(
    private val repository: CourseRepository,
) {
    suspend operator fun invoke(): Result<List<Course>> =
        repository.getCourses()
}
