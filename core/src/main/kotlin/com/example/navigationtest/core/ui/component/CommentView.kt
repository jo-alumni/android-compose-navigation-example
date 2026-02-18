package com.example.navigationtest.core.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigationtest.core.ui.theme.AppTheme

@Composable
fun CommentView(
    modifier: Modifier = Modifier,
    name: String,
    userId: String,
    content: String,
    onClickComment: (() -> Unit)? = null,
    onClickProfile: (() -> Unit)? = null,
) {
}

@Preview
@Composable
fun CommentViewPreview() {
    AppTheme {
        CommentView(
            name = "User Name",
            userId = TODO(),
            content = TODO(),
            onClickComment = TODO(),
            onClickProfile = TODO(),
        )
    }
}
