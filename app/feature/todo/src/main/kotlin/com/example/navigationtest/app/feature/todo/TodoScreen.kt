package com.example.navigationtest.app.feature.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.navigationtest.app.core.extension.copy
import com.example.navigationtest.app.core.ui.component.PostView
import com.example.navigationtest.app.feature.todo.contract.TodoEvent
import com.example.navigationtest.app.feature.todo.contract.TodoState
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun TodoRoot(
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.collectAsState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect {
        when (it) {
            is TodoEvent.LoadTodos -> {
                scope.launch { lazyListState.animateScrollToItem(0) }
            }
        }
    }

    TodoScreen(
        modifier = modifier,
        uiState = uiState,
        lazyListState = lazyListState,
        onInputValueChange = viewModel::changeInput,
        registerTodo = viewModel::registerTodo,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TodoScreen(
    uiState: TodoState,
    onInputValueChange: (String) -> Unit,
    registerTodo: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {},
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = FloatingActionButtonDefaults.shape,
                onClick = { /*TODO*/ },
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues.copy(bottom = 0.dp)),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = uiState.input,
                    onValueChange = onInputValueChange,
                    placeholder = { Text(text = "Todoを入力") },
                )
                Button(
                    onClick = registerTodo,
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(text = "登録")
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
                contentPadding = paddingValues.copy(top = 0.dp, start = 0.dp, end = 0.dp),
            ) {
                items(items = uiState.todos, key = { it.id }) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        PostView(
                            modifier = Modifier.fillMaxWidth(),
                            name = it.id.toString(),
                            userId = "",
                            content = it.content,
                        )
                        HorizontalDivider()
                    }
                }
            }

        }
    }
}

// private class UiStateParameterProvider : PreviewParameterProvider<TodoState> {
//     private val tweets = (1..5).map {
//         Post(
//             id = it,
//             userId = it,
//             title = "title_$it",
//             body = "body_$it",
//         )
//     }
//
//     override val values: Sequence<TodoState> = sequenceOf(
//         TodoState.Stable(
//             posts = tweets,
//             page = 1,
//             canLoadMore = true,
//         ),
//         TodoState.Loading(
//             posts = listOf(), type = TodoState.Loading.Type.REFRESH,
//             page = 1,
//             canLoadMore = true,
//         ),
//         TodoState.Loading(
//             posts = tweets, type = TodoState.Loading.Type.REFRESH,
//             page = 1,
//             canLoadMore = true,
//         ),
//         TodoState.Loading(
//             posts = tweets, type = TodoState.Loading.Type.LOAD_MORE,
//             page = 1,
//             canLoadMore = true,
//         ),
//         TodoState.Error(
//             posts = listOf(), cause = Exception("error"),
//             page = 1,
//             canLoadMore = true,
//         ),
//         TodoState.Error(
//             posts = tweets, cause = Exception("error"),
//             page = 1,
//             canLoadMore = true,
//         ),
//     )
// }
//
// @Preview(showBackground = true)
// @Composable
// private fun TodoScreenPreview(
//     @PreviewParameter(UiStateParameterProvider::class) uiState: TodoState,
// ) {
//     AppTheme {
//         TodoScreen(
//             uiState = uiState,
//             drawerState = rememberDrawerState(DrawerValue.Closed),
//             snackbarHostState = remember { SnackbarHostState() },
//             navigatePostDetail = {},
//             onRefresh = {},
//             onLoadMore = {},
//         )
//     }
// }
