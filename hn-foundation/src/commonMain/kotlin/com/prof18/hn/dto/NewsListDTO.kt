package com.prof18.hn.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString

@Serializable
data class NewsListDTO(
    val news: List<NewsDTO>
): BaseDTO() {

    override fun deserialize(jsonString: String): BaseDTO {
        val newsListDTO: NewsListDTO = json.decodeFromString(jsonString)
        newsListDTO.makeFrozen()
        return newsListDTO
    }
}