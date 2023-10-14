package com.designclub.ultimate.fc24

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.news_card.imageCard

class NewsActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        var image = "&"
        previewAll.visibility = View.GONE
        progress.visibility = View.VISIBLE
        val idComing = intent.getStringExtra("id")
        Log.i("id",idComing.toString())
        database = FirebaseDatabase.getInstance().getReference("news")
        database.child(idComing.toString()).get().addOnSuccessListener {
            categoryTitle.text = it.child("category").value.toString()
            titleNews.text = it.child("title").value.toString()
            Log.i("title",it.child("title").value.toString())
            image = it.child("image").value.toString()
            if (image.contains("=large")) {
                image = image.replace("=large", "=medium")
            } else if (image.contains("=small")) {
                image = image.replace("=small", "=medium")
            }
            Glide.with(this)

                .load(image)
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
                    .load(image)
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
            textNews1.text = it.child("text1").value.toString().replace("\\n","\n")
            textNews2.text = it.child("text2").value.toString().replace("\\n","\n")
            textNews3.text = it.child("text3").value.toString().replace("\\n","\n")
        }

        back.setOnClickListener{
            finish()
        }



        close.setOnClickListener{
            previewAll.visibility = View.GONE
        }
    }

}