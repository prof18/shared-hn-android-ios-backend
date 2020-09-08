package com.prof18.hn.dto

data class NewsDTO(
    val author: String,
    val id: Long,
    val score: Int,
    val timestamp: Long,
    val title: String,
    val type: String,
    val url: String
)