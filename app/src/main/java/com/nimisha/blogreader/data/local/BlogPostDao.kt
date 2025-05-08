package com.nimisha.blogreader.data.local

import androidx.room.*

@Dao
interface BlogPostDao {
    @Query("SELECT * FROM blog_posts")
    suspend fun getAll(): List<BlogPostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<BlogPostEntity>)

    @Query("DELETE FROM blog_posts")
    suspend fun clear()
}
