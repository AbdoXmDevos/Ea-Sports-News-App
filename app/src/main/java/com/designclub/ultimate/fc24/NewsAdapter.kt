package com.designclub.ultimate.fc24

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.designclub.ultimate.fc24.databinding.NewsCardBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.HolderNews> {

    private var newsArrayList: ArrayList<ModelNews>
    private val context: Context
    private lateinit var binding: NewsCardBinding
    private val mClickListener: (categories: ModelNews) -> Unit

    constructor(
        newsArrayList: ArrayList<ModelNews>,
        context: Context,
        mClickListener: (categories: ModelNews) -> Unit
    ) {
        this.newsArrayList = newsArrayList
        this.context = context
        this.mClickListener = mClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.HolderNews {
        binding = NewsCardBinding.inflate(LayoutInflater.from(context),parent,false)

        return HolderNews(binding.root)
    }

    override fun onBindViewHolder(holder: NewsAdapter.HolderNews, position: Int) {
        val model = newsArrayList[position]
        val id = model.id
        val title = model.title
        val new = model.new
        val image = model.image


        Glide.with(context)
            .load(image)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .into(holder.news_image)
        holder.news_title.text = title

        holder.root.setOnClickListener{
            mClickListener(model)
        }
        if (!new) holder.new_news.visibility = View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    inner class HolderNews(itemView: View): RecyclerView.ViewHolder(itemView){
        var news_title = binding.titleCard
        var root = itemView.rootView
        var news_image = binding.imageCard
        var new_news = binding.newNews
    }
}