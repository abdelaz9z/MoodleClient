package io.versology.moodleclient.core.domain.repository

import io.versology.moodleclient.core.domain.model.GradeItem

/**
 * Grade data operations contract.
 * Single Responsibility: handles only grade-related data retrieval.
 */
interface GradeRepository {

    suspend fun getGrades(courseId: Int): Result<List<GradeItem>>
}
