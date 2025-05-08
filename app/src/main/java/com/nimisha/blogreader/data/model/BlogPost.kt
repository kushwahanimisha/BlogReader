package com.nimisha.blogreader.data.model

data class BlogPost(
    val id: Int,
    val title: Rendered,
    val link: String
)

data class Rendered(
    val rendered: String
)
