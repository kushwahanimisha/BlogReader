package com.nimisha.blogreader.ui.bloglist

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nimisha.blogreader.data.model.BlogPost

@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogListViewModel = viewModel()) {
    val listState = rememberLazyListState()
    val blogList = viewModel.blogList

    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
        items(blogList) { blog ->
            Text(
                text = blog.title.rendered,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val encodedUrl = Uri.encode(blog.link)
                        navController.navigate("detail/$encodedUrl")
                    }
                    .padding(12.dp)
            )
        }

        item {
            // Optional loading indicator
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
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

