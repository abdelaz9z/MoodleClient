package io.versology.moodleclient.feature.coursedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.versology.moodleclient.core.common.UiState
import io.versology.moodleclient.core.domain.model.CourseSection
import io.versology.moodleclient.core.domain.model.GradeItem
import io.versology.moodleclient.core.domain.usecase.GetCourseContentsUseCase
import io.versology.moodleclient.core.domain.usecase.GetGradesUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CourseDetailState(
    val courseName: String = "",
    val sectionsState: UiState<List<CourseSection>> = UiState.Loading,
    val gradesState: UiState<List<GradeItem>> = UiState.Loading,
)

class CourseDetailViewModel(
    private val courseId: Int,
    private val courseName: String,
    private val getCourseContents: GetCourseContentsUseCase,
    private val getGrades: GetGradesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CourseDetailState(courseName = courseName))
    val state: StateFlow<CourseDetailState> = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                sectionsState = UiState.Loading,
                gradesState = UiState.Loading,
            )

            val sectionsDeferred = async { getCourseContents(courseId) }
            val gradesDeferred = async { getGrades(courseId) }

            val sectionsResult = sectionsDeferred.await()
            val gradesResult = gradesDeferred.await()

            _state.value = _state.value.copy(
                sectionsState = sectionsResult.fold(
                    onSuccess = { UiState.Success(it) },
                    onFailure = { UiState.Error(it.message ?: "Failed to load sections") }
                ),
                gradesState = gradesResult.fold(
                    onSuccess = { UiState.Success(it) },
                    onFailure = { UiState.Error(it.message ?: "Failed to load grades") }
                ),
            )
        }
    }
}
