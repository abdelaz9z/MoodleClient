package io.versology.moodleclient.core.di

import io.ktor.client.HttpClient
import io.versology.moodleclient.core.data.SessionManager
import io.versology.moodleclient.core.data.network.MoodleApi
import io.versology.moodleclient.core.data.repository.AuthRepositoryImpl
import io.versology.moodleclient.core.data.repository.CourseRepositoryImpl
import io.versology.moodleclient.core.data.repository.GradeRepositoryImpl
import io.versology.moodleclient.core.domain.repository.AuthRepository
import io.versology.moodleclient.core.domain.repository.CourseRepository
import io.versology.moodleclient.core.domain.repository.GradeRepository
import io.versology.moodleclient.core.domain.usecase.GetCourseContentsUseCase
import io.versology.moodleclient.core.domain.usecase.GetCoursesUseCase
import io.versology.moodleclient.core.domain.usecase.GetGradesUseCase
import io.versology.moodleclient.core.domain.usecase.LoginWithCredentialsUseCase
import io.versology.moodleclient.core.domain.usecase.LoginWithTokenUseCase
import io.versology.moodleclient.core.domain.usecase.LogoutUseCase
import io.versology.moodleclient.feature.coursedetail.CourseDetailViewModel
import io.versology.moodleclient.feature.courses.CoursesViewModel
import io.versology.moodleclient.feature.grades.GradesViewModel
import io.versology.moodleclient.feature.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Platform-specific HttpClient engine factory.
 * Android: OkHttp with Chucker interceptor
 * iOS: Darwin
 */
expect fun createPlatformHttpClient(): HttpClient

val appModule = module {

    // Data Layer

    single { createPlatformHttpClient() }

    single { SessionManager() }

    single { MoodleApi(client = get(), sessionManager = get()) }

    // Repositories (DIP: bind interface → impl)

    single<AuthRepository> { AuthRepositoryImpl(api = get(), sessionManager = get()) }

    single<CourseRepository> { CourseRepositoryImpl(api = get()) }

    single<GradeRepository> { GradeRepositoryImpl(api = get()) }

    // Use Cases (SRP: one operation per class)

    factory { LoginWithCredentialsUseCase(repository = get()) }

    factory { LoginWithTokenUseCase(repository = get()) }

    factory { LogoutUseCase(repository = get()) }

    factory { GetCoursesUseCase(repository = get()) }

    factory { GetCourseContentsUseCase(repository = get()) }

    factory { GetGradesUseCase(repository = get()) }

    // ViewModels

    viewModel {
        LoginViewModel(
            loginWithCredentialsUseCase = get(),
            loginWithTokenUseCase = get(),
            logoutUseCase = get(),
            authRepository = get()
        )
    }

    viewModel { CoursesViewModel(getCoursesUseCase = get()) }

    viewModel { params ->
        CourseDetailViewModel(
            courseId = params.get(),
            courseName = params.get(),
            getCourseContents = get(),
            getGrades = get()
        )
    }

    viewModel { GradesViewModel(getCoursesUseCase = get(), getGradesUseCase = get()) }
}
