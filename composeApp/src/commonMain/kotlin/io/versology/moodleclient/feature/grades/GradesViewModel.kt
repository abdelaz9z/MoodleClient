package io.versology.moodleclient.feature.grades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.versology.moodleclient.core.common.UiState
import io.versology.moodleclient.core.domain.model.Course
import io.versology.moodleclient.core.domain.model.GradeItem
import io.versology.moodleclient.core.domain.usecase.GetCoursesUseCase
import io.versology.moodleclient.core.domain.usecase.GetGradesUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CourseGradeSummary(
    val course: Course,
    val gradeItems: List<GradeItem>,
    val courseTotal: GradeItem?,
)

class GradesViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getGradesUseCase: GetGradesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<CourseGradeSummary>>>(UiState.Loading)
    val state: StateFlow<UiState<List<CourseGradeSummary>>> = _state.asStateFlow()

    init {
        loadGrades()
    }

    fun loadGrades() {
        viewModelScope.launch {
            _state.value = UiState.Loading

            val coursesResult = getCoursesUseCase()

            coursesResult.fold(
                onSuccess = { courses ->
                    val gradeResults = courses.map { course ->
                        async {
                            val gradesResult = getGradesUseCase(course.id)
                            gradesResult.getOrNull()?.let { items ->
                                val courseTotal = items.find { it.itemType == "course" }
                                val gradeItems = items.filter { it.itemType != "course" }
                                CourseGradeSummary(
                                    course = course,
                                    gradeItems = gradeItems,
                                    courseTotal = courseTotal,
                                )
                            }
                        }
                    }.awaitAll().filterNotNull()

                    _state.value = UiState.Success(gradeResults)
                },
                onFailure = { error ->
                    _state.value = UiState.Error(
                        error.message ?: "Failed to load grades"
                    )
                }
            )
        }
    }
}
