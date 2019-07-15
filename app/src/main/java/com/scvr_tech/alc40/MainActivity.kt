package com.scvr_tech.alc40

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loadWebView(view : View){
        startActivity(Intent(this, WebActivity::class.java))
    }

    fun loadProfile(view : View) {
        startActivity(Intent(this, ProfileActivity::class.java))
    }
}
