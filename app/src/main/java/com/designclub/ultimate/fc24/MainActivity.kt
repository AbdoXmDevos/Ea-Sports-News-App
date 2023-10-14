package com.designclub.ultimate.fc24

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designclub.ultimate.fc24.NewsAdapter
import com.designclub.ultimate.fc24.databinding.NewsCardBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.recyclerView
import java.util.Collections

private lateinit var database: DatabaseReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference.child("news")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    var list: ArrayList<ModelNews> = arrayListOf()
                    for (postSnapshot in dataSnapshot.children) {
                        var data = postSnapshot.getValue(ModelNews::class.java)
                        var th = data!!.image
                        if (th.contains("=medium")) {
                            th = th.replace("=medium", "=small")
                        } else if (th.contains("=large")) {
                            th = th.replace("=large", "=small")
                        }
                        list.add(0,data!!)
                    }
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = NewsAdapter(
                        newsArrayList = list,
                        context = this@MainActivity,
                        mClickListener = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    NewsActivity::class.java
                                ).putExtra("id",it.id.toString())
                            )
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