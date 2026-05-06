package io.versology.moodleclient.feature.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.versology.moodleclient.core.common.UiState
import io.versology.moodleclient.core.domain.model.Course
import io.versology.moodleclient.core.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Course>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Course>>> = _state.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            getCoursesUseCase()
                .onSuccess { courses ->
                    _state.value = UiState.Success(courses)
                }
                .onFailure { error ->
                    _state.value = UiState.Error(
                        error.message ?: "Failed to load courses"
                    )
                }
        }
    }
}
