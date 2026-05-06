package io.versology.moodleclient.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// =================================================================================================
// Color Palette
// =================================================================================================

// Primary – Deep Indigo
private val PrimaryLight = Color(0xFF4355B9)
private val OnPrimaryLight = Color(0xFFFFFFFF)
private val PrimaryContainerLight = Color(0xFFDEE0FF)
private val OnPrimaryContainerLight = Color(0xFF00105C)

// Secondary – Teal
private val SecondaryLight = Color(0xFF006B5E)
private val OnSecondaryLight = Color(0xFFFFFFFF)
private val SecondaryContainerLight = Color(0xFF7CF8E1)
private val OnSecondaryContainerLight = Color(0xFF00201B)

// Tertiary – Amber
private val TertiaryLight = Color(0xFF8B5000)
private val OnTertiaryLight = Color(0xFFFFFFFF)
private val TertiaryContainerLight = Color(0xFFFFDCBE)
private val OnTertiaryContainerLight = Color(0xFF2D1600)

// Surface
private val SurfaceLight = Color(0xFFFBF8FF)
private val OnSurfaceLight = Color(0xFF1B1B21)
private val SurfaceVariantLight = Color(0xFFE3E1EC)
private val OnSurfaceVariantLight = Color(0xFF46464F)
private val SurfaceContainerLight = Color(0xFFF0EDF5)
private val SurfaceContainerHighLight = Color(0xFFEAE7F0)

// Error
private val ErrorLight = Color(0xFFBA1A1A)
private val OnErrorLight = Color(0xFFFFFFFF)
private val ErrorContainerLight = Color(0xFFFFDAD6)

private val OutlineLight = Color(0xFF777680)
private val OutlineVariantLight = Color(0xFFC7C5D0)

// =================================================================================================
// Dark Palette
// =================================================================================================

private val PrimaryDark = Color(0xFFBAC3FF)
private val OnPrimaryDark = Color(0xFF08218A)
private val PrimaryContainerDark = Color(0xFF293CA0)
private val OnPrimaryContainerDark = Color(0xFFDEE0FF)

private val SecondaryDark = Color(0xFF5CDBC5)
private val OnSecondaryDark = Color(0xFF003830)
private val SecondaryContainerDark = Color(0xFF005046)
private val OnSecondaryContainerDark = Color(0xFF7CF8E1)

private val TertiaryDark = Color(0xFFFFB871)
private val OnTertiaryDark = Color(0xFF4A2800)
private val TertiaryContainerDark = Color(0xFF6A3C00)
private val OnTertiaryContainerDark = Color(0xFFFFDCBE)

private val SurfaceDark = Color(0xFF131318)
private val OnSurfaceDark = Color(0xFFE4E1E9)
private val SurfaceVariantDark = Color(0xFF46464F)
private val OnSurfaceVariantDark = Color(0xFFC7C5D0)
private val SurfaceContainerDark = Color(0xFF1F1F25)
private val SurfaceContainerHighDark = Color(0xFF2A2A30)

private val ErrorDark = Color(0xFFFFB4AB)
private val OnErrorDark = Color(0xFF690005)
private val ErrorContainerDark = Color(0xFF93000A)

private val OutlineDark = Color(0xFF918F9A)
private val OutlineVariantDark = Color(0xFF46464F)

// =================================================================================================
// Color Schemes
// =================================================================================================

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    surfaceContainer = SurfaceContainerLight,
    surfaceContainerHigh = SurfaceContainerHighLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    outline = OutlineLight,
    outlineVariant = OutlineVariantLight,
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    surfaceContainer = SurfaceContainerDark,
    surfaceContainerHigh = SurfaceContainerHighDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    outline = OutlineDark,
    outlineVariant = OutlineVariantDark,
)

// =================================================================================================
// Typography
// =================================================================================================

private val MoodleTypography = Typography(
    displayLarge = TextStyle(fontSize = 57.sp, fontWeight = FontWeight.Normal, lineHeight = 64.sp),
    displayMedium = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Normal, lineHeight = 52.sp),
    displaySmall = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Normal, lineHeight = 44.sp),
    headlineLarge = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.SemiBold, lineHeight = 40.sp),
    headlineMedium = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.SemiBold, lineHeight = 36.sp),
    headlineSmall = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, lineHeight = 32.sp),
    titleLarge = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, lineHeight = 28.sp),
    titleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, lineHeight = 24.sp, letterSpacing = 0.15.sp),
    titleSmall = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = 20.sp, letterSpacing = 0.1.sp),
    bodyLarge = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 24.sp, letterSpacing = 0.5.sp),
    bodyMedium = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = 20.sp, letterSpacing = 0.25.sp),
    bodySmall = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, lineHeight = 16.sp, letterSpacing = 0.4.sp),
    labelLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = 20.sp, letterSpacing = 0.1.sp),
    labelMedium = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium, lineHeight = 16.sp, letterSpacing = 0.5.sp),
    labelSmall = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Medium, lineHeight = 16.sp, letterSpacing = 0.5.sp),
)

// =================================================================================================
// Shapes
// =================================================================================================
private val MoodleShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp),
)

// =================================================================================================
// Custom Colors
// =================================================================================================

object MoodleColors {
    val gradeExcellent = Color(0xFF2E7D32)
    val gradeGood = Color(0xFF4CAF50)
    val gradeAverage = Color(0xFFF9A825)
    val gradePoor = Color(0xFFE53935)
    val gradeNone = Color(0xFF9E9E9E)

    fun gradeColor(percentage: Float?): Color {
        return when {
            percentage == null -> gradeNone
            percentage >= 80f -> gradeExcellent
            percentage >= 60f -> gradeGood
            percentage >= 40f -> gradeAverage
            else -> gradePoor
        }
    }
}

// =================================================================================================
// Theme
// =================================================================================================
@Composable
fun MoodleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MoodleTypography,
        shapes = MoodleShapes,
        content = content
    )
}
