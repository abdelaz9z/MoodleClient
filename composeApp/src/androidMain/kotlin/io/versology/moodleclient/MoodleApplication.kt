package io.versology.moodleclient

import android.app.Application

class MoodleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MoodleApplication
            private set
    }
}
