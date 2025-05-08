package com.nimisha.blogreader.ui.bloglist

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogListViewModel = viewModel()) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(viewModel.blogList) { blog ->
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
    }
}
