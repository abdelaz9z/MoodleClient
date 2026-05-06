package io.versology.moodleclient.feature.grades.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.versology.moodleclient.core.designsystem.theme.MoodleColors
import io.versology.moodleclient.core.domain.model.GradeItem

@Composable
fun GradeItemRow(
    gradeItem: GradeItem,
    modifier: Modifier = Modifier
) {
    val percentage = if (gradeItem.gradeMax > 0 && gradeItem.gradeRaw != null) {
        (gradeItem.gradeRaw / gradeItem.gradeMax) * 100f
    } else null

    val progressAnim by animateFloatAsState(
        targetValue = percentage?.div(100f) ?: 0f,
        animationSpec = tween(durationMillis = 600)
    )

    val gradeColor = MoodleColors.gradeColor(percentage)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(gradeColor.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = when (gradeItem.module) {
                    "assign" -> "📝"
                    "quiz" -> "❓"
                    "forum" -> "💬"
                    else -> if (gradeItem.itemType == "course") "🎓" else "📋"
                },
                style = MaterialTheme.typography.titleSmall,
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = gradeItem.name ?: "Course Total",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progressAnim)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(2.dp))
                        .background(gradeColor)
                )
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = gradeItem.gradeFormatted,
                style = MaterialTheme.typography.titleSmall,
                color = gradeColor,
            )
            Text(
                text = gradeItem.rangeFormatted.replace("&ndash;", "–"),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
