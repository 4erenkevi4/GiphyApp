package com.example.giphyapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyapp.dataModel.GifModel
import com.example.giphyapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(private val repository: Repository) : ViewModel() {
    val searshData: MutableLiveData<Response<GifModel>> = MutableLiveData()
    val TrendData: MutableLiveData<Response<GifModel>> = MutableLiveData()
    fun searshGifs(query: String) {
        viewModelScope.launch {
            val value = repository.searshGifs(query)
            searshData.value = value
        }
    }

    fun trendGifs() {
        viewModelScope.launch {
            val value = repository.trendGifs()
            TrendData.value = value
        }
    }
}