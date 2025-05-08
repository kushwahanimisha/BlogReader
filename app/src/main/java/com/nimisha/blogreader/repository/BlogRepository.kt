package com.nimisha.blogreader.repository

import com.nimisha.blogreader.data.model.BlogPost
import com.nimisha.blogreader.data.network.RetrofitClient

class BlogRepository {
    suspend fun getBlogPosts(): List<BlogPost> {
        return RetrofitClient.api.getPosts()
    }
}
