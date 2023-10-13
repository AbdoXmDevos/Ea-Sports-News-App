package com.designclub.ultimate.fc24

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.news_card.imageCard

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        previewAll.visibility = View.GONE
        progress.visibility = View.VISIBLE

        Glide.with(this)
            .load("https://pbs.twimg.com/media/F8Vb9--WEAAGKhf?format=jpg&name=medium")
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // Handle failure if needed
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress.visibility = View.GONE

                    return false
                }
            })
            .into(imageView5)

        imageView5.setOnClickListener{
            previewAll.visibility = View.VISIBLE
            loadingPreview.visibility = View.VISIBLE
            Glide.with(this)
                .load("https://pbs.twimg.com/media/F8Vb9--WEAAGKhf?format=jpg&name=large")
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Handle failure if needed
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loadingPreview.visibility = View.GONE

                        return false
                    }
                })
                .into(preview)
        }
        close.setOnClickListener{
            previewAll.visibility = View.GONE
        }
    }

}