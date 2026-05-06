package io.versology.moodleclient.feature.coursedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.versology.moodleclient.core.common.UiState
import io.versology.moodleclient.core.designsystem.component.CoursesLoadingView
import io.versology.moodleclient.core.designsystem.component.ErrorView
import io.versology.moodleclient.core.designsystem.component.GradeLoadingView
import io.versology.moodleclient.core.designsystem.component.SectionHeader
import io.versology.moodleclient.core.designsystem.icon.MoodleClientIcons
import io.versology.moodleclient.core.designsystem.theme.MoodleColors
import io.versology.moodleclient.core.domain.model.CourseModule
import io.versology.moodleclient.core.domain.model.CourseSection
import io.versology.moodleclient.core.domain.model.GradeItem
import io.versology.moodleclient.feature.grades.component.GradeItemRow
import moodleclient.composeapp.generated.resources.Res
import moodleclient.composeapp.generated.resources.course_detail_course_total
import moodleclient.composeapp.generated.resources.course_detail_grade_format
import moodleclient.composeapp.generated.resources.course_detail_no_content
import moodleclient.composeapp.generated.resources.course_detail_tab_content
import moodleclient.composeapp.generated.resources.course_detail_tab_grades
import moodleclient.composeapp.generated.resources.grades_grade_items_header
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    viewModel: CourseDetailViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    var selectedTab by remember { mutableStateOf(value = 0) }
    val tabs = listOf(
        stringResource(Res.string.course_detail_tab_content),
        stringResource(Res.string.course_detail_tab_grades)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.courseName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = MoodleClientIcons.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            PrimaryTabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            )
                        }
                    )
                }
            }

            when (selectedTab) {
                0 -> ContentTab(state.sectionsState, onRetry = { viewModel.loadData() })
                1 -> GradesTab(state.gradesState, onRetry = { viewModel.loadData() })
            }
        }
    }
}

@Composable
private fun ContentTab(
    state: UiState<List<CourseSection>>,
    onRetry: () -> Unit,
) {
    when (state) {
        is UiState.Loading -> CoursesLoadingView()
        is UiState.Error -> ErrorView(message = state.message, onRetry = onRetry)
        is UiState.Success -> {
            val sections = state.data.filter { it.modules.isNotEmpty() || it.name != "General" }

            if (sections.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("📚", style = MaterialTheme.typography.displayMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            stringResource(Res.string.course_detail_no_content),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                ) {
                    sections.forEach { section ->
                        item(key = "header_${section.id}") {
                            SectionHeader(
                                title = section.name.ifEmpty { "Section ${section.sectionIndex}" },
                                moduleCount = section.modules.size,
                            )
                        }

                        items(
                            items = section.modules,
                            key = { "module_${it.id}" }
                        ) { module ->
                            ModuleRow(module = module)
                        }

                        item(key = "divider_${section.id}") {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ModuleRow(module: CourseModule) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(
                    when (module.purpose) {
                        "assessment" -> MaterialTheme.colorScheme.tertiaryContainer
                        "collaboration" -> MaterialTheme.colorScheme.secondaryContainer
                        else -> MaterialTheme.colorScheme.primaryContainer
                    }
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = when (module.type) {
                    "assign" -> MoodleClientIcons.ModuleAssignment
                    "quiz" -> MoodleClientIcons.ModuleQuiz
                    "forum" -> MoodleClientIcons.ModuleForum
                    "resource" -> MoodleClientIcons.ModuleResource
                    "page" -> MoodleClientIcons.ModulePage
                    "url" -> MoodleClientIcons.ModuleUrl
                    "folder" -> MoodleClientIcons.ModuleFolder
                    else -> MoodleClientIcons.ModuleDefault
                },
                contentDescription = module.type,
                modifier = Modifier.size(18.dp),
                tint = when (module.purpose) {
                    "assessment" -> MaterialTheme.colorScheme.onTertiaryContainer
                    "collaboration" -> MaterialTheme.colorScheme.onSecondaryContainer
                    else -> MaterialTheme.colorScheme.onPrimaryContainer
                },
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = module.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = module.typePlural.ifEmpty { module.type.replaceFirstChar { it.uppercase() } },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun GradesTab(
    state: UiState<List<GradeItem>>,
    onRetry: () -> Unit,
) {
    when (state) {
        is UiState.Loading -> GradeLoadingView()
        is UiState.Error -> ErrorView(message = state.message, onRetry = onRetry)
        is UiState.Success -> {
            val items = state.data
            val courseTotal = items.find { it.itemType == "course" }
            val gradeItems = items.filter { it.itemType != "course" }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                if (courseTotal != null) {
                    item(key = "total") {
                        CourseTotalCard(courseTotal)
                    }
                }

                item(key = "grade_header") {
                    SectionHeader(
                        title = stringResource(Res.string.grades_grade_items_header),
                        moduleCount = gradeItems.size,
                    )
                }

                items(
                    items = gradeItems,
                    key = { "grade_${it.id}" }
                ) { item ->
                    GradeItemRow(gradeItem = item)
                }
            }
        }
    }
}

@Composable
private fun CourseTotalCard(courseTotal: GradeItem) {
    val percentage = if (courseTotal.gradeMax > 0 && courseTotal.gradeRaw != null) {
        (courseTotal.gradeRaw / courseTotal.gradeMax) * 100f
    } else null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = stringResource(Res.string.course_detail_course_total),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(
                        Res.string.course_detail_grade_format,
                        courseTotal.gradeFormatted,
                        courseTotal.gradeMax.toInt()
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                )
            }

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MoodleColors.gradeColor(percentage).copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = courseTotal.percentageFormatted.replace(" %", "%"),
                    style = MaterialTheme.typography.titleSmall,
                    color = MoodleColors.gradeColor(percentage),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
