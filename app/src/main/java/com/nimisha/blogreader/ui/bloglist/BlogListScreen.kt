package com.nimisha.blogreader.ui.bloglist

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nimisha.blogreader.data.model.BlogPost
import com.nimisha.blogreader.data.model.Rendered

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogListViewModel = viewModel()) {
    val listState = rememberLazyListState()
    val blogList = viewModel.blogList

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Latest Blogs")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->

        LazyColumn(state = listState, modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(blogList) { blog ->
                BlogItem(blog = blog) {
                    val encodedUrl = Uri.encode(blog.link)
                    navController.navigate("detail/$encodedUrl")
                }
            }

            item {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index == blogList.lastIndex
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            viewModel.loadNextPage()
        }
    }
}



