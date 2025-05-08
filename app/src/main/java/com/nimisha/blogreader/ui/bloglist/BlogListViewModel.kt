package com.nimisha.blogreader.ui.bloglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimisha.blogreader.data.model.BlogPost
import com.nimisha.blogreader.repository.BlogRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class BlogListViewModel : ViewModel() {
    private val repo = BlogRepository()

    var blogList by mutableStateOf<List<BlogPost>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            blogList = repo.getBlogPosts()
        }
    }
}
