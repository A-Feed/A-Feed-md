package com.capstone.afeed.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.capstone.afeed.repository.ArticlesRepository

class HomeViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    fun getAllNews() = liveData {
        emitSource(articlesRepository.getNews())
    }
}