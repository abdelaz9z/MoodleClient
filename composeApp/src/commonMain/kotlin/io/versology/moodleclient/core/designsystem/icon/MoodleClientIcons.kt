package io.versology.moodleclient.core.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Single source of truth for all icons used in the Moodle Client app.
 *
 * Usage: `MoodleClientIcons.Logout`, `MoodleClientIcons.CoursesSelected`, etc.
 *
 * Centralizing icons here makes it easy to:
 * - Swap icon sets (e.g. Material → custom SVGs)
 * - Audit which icons the app uses
 * - Maintain consistency across all screens
 */
object MoodleClientIcons {

    // Navigation
    val CoursesSelected: ImageVector = Icons.Filled.MenuBook
    val CoursesUnselected: ImageVector = Icons.Outlined.MenuBook
    val GradesSelected: ImageVector = Icons.Filled.EmojiEvents
    val GradesUnselected: ImageVector = Icons.Outlined.EmojiEvents
    val ArrowBack: ImageVector = Icons.AutoMirrored.Filled.ArrowBack

    // Actions

    val Logout: ImageVector = Icons.AutoMirrored.Filled.Logout
    val ExpandMore: ImageVector = Icons.Filled.KeyboardArrowDown
    val ExpandLess: ImageVector = Icons.Filled.KeyboardArrowUp

    // Login

    val School: ImageVector = Icons.Filled.School
    val PasswordVisible: ImageVector = Icons.Filled.Visibility
    val PasswordHidden: ImageVector = Icons.Filled.VisibilityOff

    // Status

    val Warning: ImageVector = Icons.Filled.Warning
    val Complete: ImageVector = Icons.Filled.CheckCircle
    val People: ImageVector = Icons.Filled.People

    // Course Module Types

    val ModuleAssignment: ImageVector = Icons.Filled.Assignment
    val ModuleQuiz: ImageVector = Icons.Filled.Quiz
    val ModuleForum: ImageVector = Icons.Filled.Forum
    val ModuleResource: ImageVector = Icons.Filled.AttachFile
    val ModulePage: ImageVector = Icons.Filled.Description
    val ModuleUrl: ImageVector = Icons.Filled.Link
    val ModuleFolder: ImageVector = Icons.Filled.Folder
    val ModuleDefault: ImageVector = Icons.Filled.Article
}
