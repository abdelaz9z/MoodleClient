package io.versology.moodleclient.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.versology.moodleclient.core.designsystem.icon.MoodleClientIcons
import io.versology.moodleclient.core.domain.repository.AuthRepository
import io.versology.moodleclient.feature.coursedetail.CourseDetailScreen
import io.versology.moodleclient.feature.coursedetail.CourseDetailViewModel
import io.versology.moodleclient.feature.courses.CoursesScreen
import io.versology.moodleclient.feature.courses.CoursesViewModel
import io.versology.moodleclient.feature.grades.GradesScreen
import io.versology.moodleclient.feature.grades.GradesViewModel
import io.versology.moodleclient.feature.login.LoginScreen
import io.versology.moodleclient.feature.login.LoginViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import moodleclient.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

// =================================================================================================
// Route Definitions
// =================================================================================================
@Serializable
object LoginRoute

@Serializable
object CoursesRoute

@Serializable
data class CourseDetailRoute(val courseId: Int, val courseName: String)

@Serializable
object GradesRoute

// =================================================================================================
// Bottom Nav Items
// =================================================================================================
data class BottomNavItem<T : Any>(
    val label: String,
    val route: T,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

// =================================================================================================
// Navigation Host
// =================================================================================================
@Composable
fun MoodleNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val authRepository: AuthRepository = koinInject()

    val coursesLabel = stringResource(Res.string.nav_courses)
    val gradesLabel = stringResource(Res.string.nav_grades)

    val bottomNavItems = remember(coursesLabel, gradesLabel) {
        listOf(
            BottomNavItem(
                label = coursesLabel,
                route = CoursesRoute,
                selectedIcon = MoodleClientIcons.CoursesSelected,
                unselectedIcon = MoodleClientIcons.CoursesUnselected,
            ),
            BottomNavItem(
                label = gradesLabel,
                route = GradesRoute,
                selectedIcon = MoodleClientIcons.GradesSelected,
                unselectedIcon = MoodleClientIcons.GradesUnselected,
            ),
        )
    }

    val showBottomBar = currentDestination?.let { dest ->
        bottomNavItems.any { dest.hasRoute(it.route::class) }
    } ?: false

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = currentDestination?.hasRoute(item.route::class) == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.label,
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                    style = MaterialTheme.typography.labelMedium,
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginRoute,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                    initialOffsetX = { 100 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                    initialOffsetX = { -100 },
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
                    targetOffsetX = { 100 },
                    animationSpec = tween(300)
                )
            },
        ) {
            composable<LoginRoute> {
                val viewModel: LoginViewModel = koinViewModel()
                LoginScreen(
                    viewModel = viewModel,
                    onLoginSuccess = {
                        navController.navigate(CoursesRoute) {
                            popUpTo(LoginRoute) { inclusive = true }
                        }
                    }
                )
            }

            composable<CoursesRoute> {
                val viewModel: CoursesViewModel = koinViewModel()
                CoursesScreen(
                    viewModel = viewModel,
                    userName = authRepository.userFirstName.ifEmpty { stringResource(Res.string.student_fallback) },
                    onCourseClick = { course ->
                        navController.navigate(
                            CourseDetailRoute(
                                courseId = course.id,
                                courseName = course.fullName,
                            )
                        )
                    },
                    onLogout = {
                        authRepository.logout()
                        navController.navigate(LoginRoute) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            composable<CourseDetailRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<CourseDetailRoute>()
                val viewModel: CourseDetailViewModel = koinViewModel {
                    parametersOf(route.courseId, route.courseName)
                }
                CourseDetailScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() },
                )
            }

            composable<GradesRoute> {
                val viewModel: GradesViewModel = koinViewModel()
                GradesScreen(viewModel = viewModel)
            }
        }
    }
}
