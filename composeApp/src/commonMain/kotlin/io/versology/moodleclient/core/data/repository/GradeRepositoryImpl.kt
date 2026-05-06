package io.versology.moodleclient.core.data.repository

import io.versology.moodleclient.core.data.mapper.toGradeItems
import io.versology.moodleclient.core.data.network.MoodleApi
import io.versology.moodleclient.core.domain.model.GradeItem
import io.versology.moodleclient.core.domain.repository.GradeRepository

class GradeRepositoryImpl(
    private val api: MoodleApi,
) : GradeRepository {

    override suspend fun getGrades(courseId: Int): Result<List<GradeItem>> {
        return runCatching { api.getGrades(courseId).toGradeItems() }
    }
}
