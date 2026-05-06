package io.versology.moodleclient.core.domain.usecase

import io.versology.moodleclient.core.domain.model.GradeItem
import io.versology.moodleclient.core.domain.repository.GradeRepository

class GetGradesUseCase(
    private val repository: GradeRepository,
) {
    suspend operator fun invoke(courseId: Int): Result<List<GradeItem>> =
        repository.getGrades(courseId)
}
