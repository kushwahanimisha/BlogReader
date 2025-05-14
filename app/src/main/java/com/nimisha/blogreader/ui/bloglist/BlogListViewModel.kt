package com.nimisha.blogreader.ui.bloglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nimisha.blogreader.data.model.BlogPost
import com.nimisha.blogreader.repository.BlogRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

open class BlogListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BlogRepository(application)
    var blogList by mutableStateOf<List<BlogPost>>(emptyList())
        private set

    private var currentPage = 1
    private var isLoading = false

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            val newPosts = repository.getBlogPosts(currentPage)
            blogList = blogList + newPosts
            currentPage++
            isLoading = false
        }
    }
}
