package com.capstone.afeed.ui.articledetail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.afeed.data.remote.response.GetArticlesResponse
import com.capstone.afeed.databinding.ActivityArticleDetailBinding
import com.capstone.afeed.utils.loadImage

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupView()
    }

    private fun setupView() {
        val dataArticle = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, GetArticlesResponse.Article::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if (dataArticle != null) {
            with(binding) {
                tvAuthorName.text = dataArticle.author
                tvSourceName.text = dataArticle.source.name
                ivArticleImageDetail.loadImage("${dataArticle.urlToImage}")
                tvArticleTitleDetail.text = dataArticle.title
                tvArticleContentDetail.text = dataArticle.description
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_post"
    }
}