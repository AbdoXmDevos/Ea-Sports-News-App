package com.designclub.ultimate.fc24

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sortBtn.setOnClickListener(){
            startActivity(Intent(this@MainActivity, NewsActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }


    }
}