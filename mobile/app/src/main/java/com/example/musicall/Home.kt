package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    lateinit var preferencias: SharedPreferences
    lateinit var cadastro: Intent
    lateinit var feed: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cadastro = Intent(this, Info0::class.java)
        feed = Intent(this, MainActivity::class.java)
    }

    fun login(view: View) {
        val intent = Intent(this, Login::class.java).apply {
        }
        startActivity(intent)
    }

    fun sair(view: View) {
        enDeslogar()
        finish()
        val intent = Intent(this, Home::class.java).apply {
        }
        startActivity(intent)
    }

    fun registrar(view: View) {

        val intent = Intent(this, ScreenRegistrar::class.java).apply {
        }
        startActivity(intent)
    }

    fun ajuda(view: View) {

        Toast.makeText(applicationContext, "Entraria na tela de ajuda!!", Toast.LENGTH_LONG).show()

        val intent = Intent(this, AboutMusicall::class.java).apply {
        }
        startActivity(intent)

    }

    fun enDeslogar() {
        val editor = preferencias.edit()
        editor.clear()
        editor.commit()
    }

}