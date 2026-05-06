package io.versology.moodleclient.core.domain.repository

import io.versology.moodleclient.core.domain.model.Course
import io.versology.moodleclient.core.domain.model.CourseSection

/**
 * Course data operations contract.
 * Single Responsibility: handles only course-related data retrieval.
 */
interface CourseRepository {

    suspend fun getCourses(): Result<List<Course>>

    suspend fun getCourseContents(courseId: Int): Result<List<CourseSection>>
}
