package com.example.navigationtest.app.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.navigationtest.app.core.ui.theme.AppTheme
import com.example.navigationtest.core.domain.entity.Comment

@Composable
fun Comment(
    comment: Comment,
    modifier: Modifier = Modifier,
) = Comment(
    modifier = modifier,
    name = comment.name,
    userId = comment.email,
    content = comment.body,
)

@Composable
fun Comment(
    name: String,
    userId: String,
    content: String,
    modifier: Modifier = Modifier,
    tailSize: Dp = 12.dp,
) {
    Card(
        modifier = modifier.padding(top = tailSize),
        shape = SpeechBubbleShape(cornerRadius = 8.dp, tailSize = tailSize),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(name)
                Text("@${userId}")
            }
            Text(content)
        }

    }
}

@Preview
@Composable
fun CommentPreview() {
    AppTheme {
        Comment(
            name = "User Name",
            userId = "user id",
            content = "content",
        )
    }
}

class SpeechBubbleShape(
    private val cornerRadius: Dp,
    private val tailSize: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val tailSizePx = with(density) { tailSize.toPx() }

        val path = Path().apply {
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height,
                    cornerRadius = CornerRadius(cornerRadiusPx),
                ),
            )

            moveTo(
                x = size.width / 4 - tailSizePx,
                y = 0f,
            )
            lineTo(
                x = size.width / 4,
                y = -tailSizePx,
            )
            lineTo(
                x = size.width / 4 + tailSizePx,
                y = 0f,
            )
            close()
        }
        return Outline.Generic(path)
    }
}
