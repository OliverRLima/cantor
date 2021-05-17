package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.conexaoApi.modelos.Dados
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class Info2 : AppCompatActivity() {
    val na: String= "Não informado"
    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info2)
        preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun finalizarCadastro(view: View) {
        val insta: TextView = findViewById(R.id.et_instagram)
        var instaTxt = insta.text.toString()

        val face: TextView = findViewById(R.id.et_facebook)
        var faceTxt = face.text.toString()

        val tele: TextView = findViewById(R.id.tv_telefone)
        var teleTxt = tele.text.toString()

        if (instaTxt.length < 2) {
            instaTxt = na
        }
        if (faceTxt.length < 2) {
            faceTxt = na
        }
        if (teleTxt.length < 2) {
            teleTxt = na
        }

        val data = intent.getStringExtra("data").toString()
        val cidade = intent.getStringExtra("cidade").toString()
        val estado = intent.getStringExtra("estado").toString()
        val instrumento = intent.getStringExtra("instrumento").toString()
        val genero = intent.getStringExtra("genero").toString()

        val dados = Dados(data, estado, cidade, faceTxt, instaTxt, teleTxt, instrumento, genero)

        val apiMusicall = ConexaoApiMusicall.criar()
        val idUsuario = preferencias.getInt("idUsuario", -1)
        val login = Intent(this, Login::class.java)

        apiMusicall.cadastrarInfo(dados, idUsuario).enqueue(object : Callback<Dados>{
            override fun onResponse(call: Call<Dados>, response: Response<Dados>) {
                when {
                    response.code() == 201 -> {
                        val editor = preferencias.edit()
                        editor.remove("idUsuario")
                        editor.commit()
                        Toast.makeText(baseContext, "Dados cadastrados", Toast.LENGTH_SHORT).show()
                        startActivity(login)
                    }
                    response.code() == 400 -> {
                        Toast.makeText(baseContext, "Não foi possível cadastrar os dados", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(baseContext, "Falha ao cadastrar os dados", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<Dados>, t: Throwable) {
                println("Falha ao cadastrar os dados ${t.message!!}")
            }

        })
    }
}
