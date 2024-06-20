package com.capstone.afeed.ui.articledetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.afeed.di.ArticlesInjection
import com.capstone.afeed.di.FishPondInjection
import com.capstone.afeed.repository.ArticlesRepository
import com.capstone.afeed.repository.FishPondRepository
import com.capstone.afeed.ui.monitoring.MonitoringViewModel
import com.capstone.afeed.ui.monitoring.MonitoringViewModelFactory

class ArticleDetailViewModelFactory private constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java)) {
            return ArticleDetailViewModel(articlesRepository) as T
//            return FishPondFormViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: ArticleDetailViewModelFactory? = null

        fun getInstance(context: Context): ArticleDetailViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ArticleDetailViewModelFactory(ArticlesInjection.provideRepository())
            }.also { INSTANCE = it }
    }
}