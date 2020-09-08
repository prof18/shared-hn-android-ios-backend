package com.prof18.hn.backend

import com.prof18.hn.dto.ListResponseDTO
import com.prof18.hn.dto.NewsDTO

interface NewsRepository {
    fun getTopStories(): ListResponseDTO<NewsDTO>
}