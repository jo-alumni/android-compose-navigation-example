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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.navigationtest.core.entity.Profile
import com.example.navigationtest.core.entity.Tweet
import com.example.navigationtest.core.ui.theme.AppTheme

@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    tweet: Tweet,
    onClickTweet: (Tweet) -> Unit = {},
    onClickProfile: (Profile) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickTweet(tweet) }
            .heightIn(min = 100.dp, max = 200.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
            .padding(all = 8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .clickable { onClickProfile(tweet.postUser) },
                model = "https://placehold.jp/200x200.png",
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Text(tweet.postUser.name)
        }
        Text(text = tweet.content, overflow = TextOverflow.Ellipsis)
    }
}

@Preview(widthDp = 300)
@Composable
private fun HomeItemPreview() {
    AppTheme {
        HomeItem(
            tweet = Tweet(
                id = 1,
                content = "content ".repeat(100),
                postUser = Profile(
                    id = 1,
                    name = "user_name",
                    description = "user_description",
                ),
            ),
        )
    }
}
