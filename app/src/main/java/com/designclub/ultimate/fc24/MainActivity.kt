package com.designclub.ultimate.fc24

import android.content.ContentValues
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.HorizontalScrollView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designclub.ultimate.fc24.NewsAdapter
import com.designclub.ultimate.fc24.databinding.NewsCardBinding
import com.google.android.material.chip.Chip
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.loading
import kotlinx.android.synthetic.main.activity_main.recyclerView
import java.util.Collections

private lateinit var database: DatabaseReference

class MainActivity : AppCompatActivity() {
    var lastClickedChip: Chip? = null
    var listCurrent: ArrayList<ModelNews> = arrayListOf()
    var list: ArrayList<ModelNews> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()

    }

    /* fun createChips(categories: List<String>) {
        for (category in categories) {
            val chip = Chip(this)
            chip.text = category
            chip.isClickable = true
            chip.isCheckable = false
            chip.setOnClickListener {
                onChipClicked(chip)

            }
            chip.setTextColor(ContextCompat.getColor(this, R.color.white))
            chip.setTypeface(null, Typeface.BOLD)
            chip.setChipBackgroundColorResource(R.color.yellow)
            chip.setChipCornerRadius(resources.getDimension(R.dimen.chip_corner_radius))
            chip.chipStrokeWidth = 0f
            chipGroup.addView(chip)
        }
    }

    private fun onChipClicked(clickedChip: Chip) {
        if (lastClickedChip == clickedChip) {
            // Clicked the same chip twice, reset all chips to 100%
            resetAllChips()
            lastClickedChip = null
        } else {
            for (i in 0 until chipGroup.childCount) {
                val chip = chipGroup.getChildAt(i) as Chip
                if (chip == clickedChip) {
                    // Clicked chip, set opacity to 100%
                    chip.alpha = 1.0f
                    lastClickedChip = chip
                } else {
                    // Other chips, set opacity to 50%
                    chip.alpha = 0.5f
                }
            }
        }
    }

    private fun resetAllChips() {
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            chip.alpha = 1.0f
        }
    }

    */

    fun getData() {
        list = arrayListOf()
        database = Firebase.database.reference.child("news")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    Handler().postDelayed({
                        loading.visibility = View.GONE
                    }, 500)
                    var categories: ArrayList<String> = arrayListOf()
                    for (postSnapshot in dataSnapshot.children) {
                        var data = postSnapshot.getValue(ModelNews::class.java)
                        var th = data!!.image
                        if (th.contains("=medium")) {
                            th = th.replace("=medium", "=small")
                        } else if (th.contains("=large")) {
                            th = th.replace("=large", "=small")
                        }
                        if (!categories.contains(data.category)) {
                            categories.add(data.category)
                        }
                        list.add(0,data!!)
                    }
                   // createChips(categories)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = NewsAdapter(
                        newsArrayList = list,
                        context = this@MainActivity,
                        mClickListener = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    NewsActivity::class.java
                                ).putExtra("id", it.id.toString())
                            )
                            recyclerView.adapter
                        }
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }
}