package io.versology.moodleclient

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.svg.SvgDecoder
import io.versology.moodleclient.core.di.appModule
import io.versology.moodleclient.core.designsystem.theme.MoodleTheme
import io.versology.moodleclient.navigation.MoodleNavGraph
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()
        }

        MoodleTheme {
            MoodleNavGraph()
        }
    }
}