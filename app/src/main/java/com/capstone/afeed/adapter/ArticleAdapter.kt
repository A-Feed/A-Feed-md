package com.capstone.afeed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.afeed.data.remote.response.GetArticlesResponse
import com.capstone.afeed.databinding.ItemArticleBinding

class ArticleAdapter :
    ListAdapter<GetArticlesResponse.Article, ArticleAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class MyViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: GetArticlesResponse.Article) {
            with(binding) {
                Glide.with(ivArticle).load("${article.urlToImage}").into(ivArticle)
                tvArticleTitle.text = article.title
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GetArticlesResponse.Article>() {
            override fun areItemsTheSame(
                oldItem: GetArticlesResponse.Article,
                newItem: GetArticlesResponse.Article
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: GetArticlesResponse.Article,
                newItem: GetArticlesResponse.Article
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}