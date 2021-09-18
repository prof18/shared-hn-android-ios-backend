package com.prof18.hn.android.client.data

import com.prof18.hn.dto.NewsListDTO
import retrofit2.http.GET

interface HNApiService  {

    @GET("hn/topStories")
    suspend fun getTopStories(): NewsListDTO

}