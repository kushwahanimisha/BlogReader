package com.nimisha.blogreader.repository

import android.content.Context
import com.nimisha.blogreader.data.local.AppDatabase
import com.nimisha.blogreader.data.model.BlogPost
import com.nimisha.blogreader.data.local.BlogPostEntity
import com.nimisha.blogreader.data.model.Rendered
import com.nimisha.blogreader.data.network.RetrofitClient

class BlogRepository(private val context: Context) {
    private val api = RetrofitClient.api
    private val dao = AppDatabase.getInstance(context).blogPostDao()

    suspend fun getBlogPosts(page: Int = 1): List<BlogPost> {
        return try {
            val response = api.getPosts(perPage = 15, page = page)
            val entities = response.map {
                BlogPostEntity(it.id, it.title.rendered, it.link)
            }
            dao.insertAll(entities) // cache
            response
        } catch (e: Exception) {
            // Offline fallback
            dao.getAll().map {
                BlogPost(it.id, Rendered(it.title), it.link)
            }
        }
    }
}

