package com.capstone.afeed.di

import com.capstone.afeed.data.remote.config.ArticlesConfig
import com.capstone.afeed.repository.ArticlesRepository

object ArticlesInjection {
    fun provideRepository(): ArticlesRepository {
        return ArticlesRepository(ArticlesConfig.getService())
    }
}