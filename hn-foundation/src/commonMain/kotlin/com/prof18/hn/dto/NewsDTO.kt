package com.prof18.hn.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString

@Serializable
data class NewsDTO(
    val author: String,
    val id: Long,
    val score: Int,
    val timestamp: Long,
    val title: String,
    val type: String,
    val url: String
) : BaseDTO() {

    override fun deserialize(jsonString: String): NewsDTO {
        val newsDTO: NewsDTO = json.decodeFromString(jsonString)
        newsDTO.makeFrozen()
        return newsDTO
    }
}