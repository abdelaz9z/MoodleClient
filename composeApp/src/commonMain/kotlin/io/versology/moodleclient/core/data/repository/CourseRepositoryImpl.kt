package io.versology.moodleclient.core.data.repository

import io.versology.moodleclient.core.data.mapper.toDomain
import io.versology.moodleclient.core.data.network.MoodleApi
import io.versology.moodleclient.core.domain.model.Course
import io.versology.moodleclient.core.domain.model.CourseSection
import io.versology.moodleclient.core.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val api: MoodleApi,
) : CourseRepository {

    override suspend fun getCourses(): Result<List<Course>> {
        return runCatching { api.getCourses().toDomain() }
    }

    override suspend fun getCourseContents(courseId: Int): Result<List<CourseSection>> {
        return runCatching { api.getCourseContents(courseId).toDomain() }
    }
}
