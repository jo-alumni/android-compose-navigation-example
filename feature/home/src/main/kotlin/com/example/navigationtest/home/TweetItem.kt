package com.example.navigationtest.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.navigationtest.core.entity.Profile
import com.example.navigationtest.core.entity.Tweet
import com.example.navigationtest.core.ui.theme.AppTheme

@Composable
fun TweetItem(
    modifier: Modifier = Modifier,
    tweet: Tweet,
    onClickTweet: (Tweet) -> Unit = {},
    onClickProfile: (Profile) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickTweet(tweet) }
            .heightIn(min = 100.dp, max = 200.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .clickable { onClickProfile(tweet.postUser) },
            model = "https://placehold.jp/200x200.png",
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column {
            Text(
                text = buildAnnotatedString {
                    append(tweet.postUser.name)
                    append(" ")
                    withStyle(SpanStyle(color = Color.Gray)) {
                        append(stringResource(R.string.user_id_prefix, tweet.postUser.id))
                    }
                },
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Text(text = tweet.content, overflow = TextOverflow.Ellipsis)
        }
    }
}

private class TweetParameterProvider : PreviewParameterProvider<Tweet> {
    override val values: Sequence<Tweet> = sequenceOf(
        // basic
        Tweet(
            id = 1,
            content = "This is a sample tweet content for the preview.",
            postUser = Profile(
                id = "sample_user",
                name = "Sample User",
                description = "This is a sample user description.",
            ),
        ),
        // Too long name
        Tweet(
            id = 1,
            content = "This is a sample tweet content for the preview.",
            postUser = Profile(
                id = "sample_user",
                name = "Sample User ".repeat(10),
                description = "This is a sample user description.",
            ),
        ),
        // Too long content
        Tweet(
            id = 1,
            content = "This is a sample tweet content for the preview.",
            postUser = Profile(
                id = "sample_user",
                name = "Sample User",
                description = "This is a sample user description.".repeat(10),
            ),
        ),
    )
}

@Preview
@Composable
private fun TweetItemPreview(@PreviewParameter(TweetParameterProvider::class) tweet: Tweet) {
    AppTheme {
        TweetItem(tweet = tweet)
    }
}
