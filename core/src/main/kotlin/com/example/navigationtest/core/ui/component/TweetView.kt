package com.example.navigationtest.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.navigation_test.core.R
import com.example.navigationtest.core.ui.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweetView(
    modifier: Modifier = Modifier,
    name: String,
    userId: String,
    content: String,
    onClickTweet: () -> Unit = {},
    onClickProfile: () -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickTweet() }
            .heightIn(min = 100.dp, max = 200.dp)
            .background(color = Color.White)
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .clickable { onClickProfile() },
            model = "https://placehold.jp/200x200.png",
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = buildAnnotatedString {
                        append(name)
                        append(" ")
                        withStyle(SpanStyle(color = Color.Gray)) {
                            append(stringResource(R.string.user_id_prefix, userId))
                        }
                    },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                IconButton(
                    modifier = Modifier.size(20.dp),
                    onClick = { showBottomSheet = true },
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                    )
                }
            }

            Text(text = content, overflow = TextOverflow.Ellipsis)
        }
    }

    if (showBottomSheet) {
        val sheetState = rememberModalBottomSheetState()
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false },
        ) {
            val list = (1..3).toList()
            list.forEachIndexed { index, it ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .clickable { scope.launch { sheetState.hide() } },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("test $it")
                }
                if (index != list.size - 1) {
                    HorizontalDivider()
                }
            }
        }
    }
}

private class TweetParameterProvider : PreviewParameterProvider<TweetParameterProvider.Parameter> {
    override val values: Sequence<Parameter> = sequenceOf(
        // default
        Parameter(),
        // Too long name
        Parameter().copy(name = "Too long name.".repeat(100)),
        // Too long userId
        Parameter().copy(userId = "too_long_user_id-".repeat(100)),
        // Too long content
        Parameter().copy(content = "Too long content.".repeat(100)),
    )

    data class Parameter(
        val name: String = "Sample User",
        val userId: String = "sample_user",
        val content: String = "This is a sample tweet content.",
    )
}

@Preview
@Composable
private fun TweetViewPreview(@PreviewParameter(TweetParameterProvider::class) parameter: TweetParameterProvider.Parameter) {
    AppTheme {
        TweetView(
            name = parameter.name,
            userId = parameter.userId,
            content = parameter.content,
        )
    }
}
