package com.prof18.hn.android.client.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.hn.android.client.data.HNApiService
import com.prof18.hn.android.client.data.model.AppState
import com.prof18.hn.android.client.data.model.News
import com.prof18.hn.android.client.data.model.NewsState
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {


    private val apiService: HNApiService by lazy {
        Retrofit.Builder()
                .baseUrl("http://192.168.0.119:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HNApiService::class.java)
    }

    private val _appState = MutableLiveData<AppState>()
    val appState: LiveData<AppState>
        get() = _appState


    fun loadData() {
        viewModelScope.launch {
            _appState.value = AppState(newsState = NewsState.Loading)
            try {
                val newsResponse = apiService.getTopStories()
                val news = newsResponse.items.map {
                    News(
                            title = it.title,
                            formattedDate = getStringTime(it.timestamp),
                            url = it.url
                    )
                }
                _appState.value = AppState(newsState = NewsState.Success(news))
            } catch (e: Exception) {
                e.printStackTrace()
                _appState.value = AppState(newsState = NewsState.Error("Something wrong here :("))
            }
        }
    }

    private fun getStringTime(time: Long): String {
        val formatter = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        return formatter.format(Date(time * 1000))

    }

}