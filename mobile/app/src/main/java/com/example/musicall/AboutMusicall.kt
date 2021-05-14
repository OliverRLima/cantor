package com.example.musicall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AboutMusicall : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_musicall)
    }

    fun voltar(view: View) {
        val intent = Intent(this, Home::class.java).apply {
        }
        startActivity(intent)
    }
}