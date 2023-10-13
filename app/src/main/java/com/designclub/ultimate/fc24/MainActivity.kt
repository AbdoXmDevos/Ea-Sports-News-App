package com.designclub.ultimate.fc24

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designclub.ultimate.fc24.NewsAdapter
import com.designclub.ultimate.fc24.databinding.NewsCardBinding
import kotlinx.android.synthetic.main.activity_main.recyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NewsAdapter(
            newsArrayList = Constants.NewsItems,
            context = this,
            mClickListener = {
                startActivity(
                    Intent(
                        this@MainActivity,
                        NewsActivity::class.java
                )
                )
            }
        )
        recyclerView.adapter = adapter

    }
}