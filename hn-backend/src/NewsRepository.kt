package com.prof18.hn.backend

import com.prof18.hn.dto.NewsDTO
import com.prof18.hn.dto.NewsListDTO

interface NewsRepository {
    fun getTopStories(): NewsListDTO
}