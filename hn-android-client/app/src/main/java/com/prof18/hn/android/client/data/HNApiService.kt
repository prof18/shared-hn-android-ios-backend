package com.prof18.hn.android.client.data

import com.prof18.hn.dto.ListResponseDTO
import com.prof18.hn.dto.NewsDTO
import retrofit2.http.GET

interface HNApiService  {

    @GET("hn/topStories")
    suspend fun getTopStories(): ListResponseDTO<NewsDTO>

}