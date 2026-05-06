package io.versology.moodleclient.core.data.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.versology.moodleclient.core.data.SessionManager
import io.versology.moodleclient.core.data.model.CourseDto
import io.versology.moodleclient.core.data.model.CourseSectionDto
import io.versology.moodleclient.core.data.model.GradeReportResponseDto
import io.versology.moodleclient.core.data.model.LoginResponseDto
import io.versology.moodleclient.core.data.model.SiteInfoDto

class MoodleApi(
    private val client: HttpClient,
    private val sessionManager: SessionManager,
) {

    suspend fun login(username: String, password: String): LoginResponseDto {
        return client.get(ApiConstants.BASE_URL + ApiConstants.LOGIN_PATH) {
            parameter("username", username)
            parameter("password", password)
            parameter("service", ApiConstants.SERVICE)
        }.body()
    }

    suspend fun getSiteInfo(): SiteInfoDto {
        return get(wsFunction = ApiConstants.FUNC_GET_SITE_INFO)
    }

    private suspend inline fun <reified T> get(
        wsFunction: String,
        additionalParams: Map<String, String> = emptyMap()
    ): T {
        return client.get(ApiConstants.BASE_URL + ApiConstants.API_PATH) {
            parameter("wstoken", sessionManager.token)
            parameter("wsfunction", wsFunction)
            parameter("moodlewsrestformat", ApiConstants.FORMAT)
            additionalParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }.body()
    }

    suspend fun getCourses(): List<CourseDto> {
        return get(
            wsFunction = ApiConstants.FUNC_GET_COURSES,
            additionalParams = mapOf("userid" to sessionManager.userId.toString())
        )
    }

    suspend fun getCourseContents(courseId: Int): List<CourseSectionDto> {
        return get(
            wsFunction = ApiConstants.FUNC_GET_CONTENTS,
            additionalParams = mapOf("courseid" to courseId.toString())
        )
    }

    suspend fun getGrades(courseId: Int): GradeReportResponseDto {
        return get(
            wsFunction = ApiConstants.FUNC_GET_GRADES,
            additionalParams = mapOf(
                "courseid" to courseId.toString(),
                "userid" to sessionManager.userId.toString()
            )
        )
    }
}
