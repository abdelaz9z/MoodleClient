package io.versology.moodleclient.core.data.network

object ApiConstants {
    const val BASE_URL = "https://moodle.itcorner.qzz.io"
    const val API_PATH = "/webservice/rest/server.php"
    const val LOGIN_PATH = "/login/token.php"
    const val SERVICE = "moodle_mobile_app"
    const val FORMAT = "json"

    // Web service function names
    const val FUNC_GET_SITE_INFO = "core_webservice_get_site_info"
    const val FUNC_GET_COURSES = "core_enrol_get_users_courses"
    const val FUNC_GET_CONTENTS = "core_course_get_contents"
    const val FUNC_GET_GRADES = "gradereport_user_get_grade_items"

    // Pre-generated test credentials (Option B)
    const val DEFAULT_TOKEN = "c269d73b8ec3265227714bf37f4dd2e4"
    const val DEFAULT_USERNAME = "student1"
    const val DEFAULT_PASSWORD = "Demo@12345"
}
