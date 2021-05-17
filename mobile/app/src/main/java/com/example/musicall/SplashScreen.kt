package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreen : AppCompatActivity() {

    lateinit var preferencias: SharedPreferences
    lateinit var login: Intent
    lateinit var feed: Intent
    lateinit var handler:Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        login = Intent(this, Home::class.java)
        feed = Intent(this, MainActivity::class.java)
        preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)

        val token = preferencias.getString("token", null)



        handler = Handler()

        if (token == null) {
            handler.postDelayed({
                startActivity(login)
            },1000)
        }

        val api = ConexaoApiMusicall.criar()
        api.verificaToken(token.toString()).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when{
                    response.code() == 200 -> {
                        handler.postDelayed({
                            startActivity(feed)
                        }, 1000)
                    }
                    response.code() == 403 -> {
                        handler.postDelayed({
                            startActivity(login)
                        }, 1000)
                    }
                    else -> {
                        println("Falha ao tentar fazer a verificação")
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Erro ao tentar fazer a verificação: ${t.message}")
            }

        })

    }
}