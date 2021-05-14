package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        println(data)
        cadastrarInfo(data, cidade, estado)
        cadastrarInstrumento(instrumento)
        cadastrarGenero(genero)
        cadastrarSociais(instaTxt, faceTxt, teleTxt)
        val feed = Intent(this, MainActivity::class.java)
        startActivity(feed)

    }

    fun cadastrarInfo(data: String, cidade: String, estado: String) {
        val apiMusicall = ConexaoApiMusicall.criar()

//        val info = Info(data, na, cidade, estado)
//
//        apiMusicall.cadastrarInfo(info).enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.code() == 201) {
//                    println("info cadastrada com sucesso")
//                    val editor = preferencias.edit()
//                    editor.putInt("idInfoUsuario", 1)
//                    editor.commit()
//                } else {
//                    println("erro ${response.code()} ao cadastrar info")
//                }
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                println("erro na chamada : ${t.message!!}")
//            }
//        })
    }

    fun cadastrarGenero(generoMusical: String) {
//        val apiMusicall = ConexaoApiMusicall.criar()
//        val genero = Genero(na, generoMusical)
//
//        apiMusicall.cadastrarGenero(genero).enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.code() == 200) {
//                    println("genero cadastrado com sucesso")
//                } else {
//                    println("erro ${response.code()} ao cadastrar genero")
//                }
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                println("erro na chamada : ${t.message!!}")
//            }
//
//        })
    }

    fun cadastrarInstrumento(instrumentoNome: String) {
//        val apiMusicall = ConexaoApiMusicall.criar()
//
//        val instrumento = Instrumento(na, instrumentoNome, na)
//
//        apiMusicall.cadastrarInstrumento(instrumento).enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.code() == 200) {
//                    println("Cadastrou Instrumento")
//                } else {
//                    println("erro ${response.code()} ao cadastrar Instrumento")
//                }
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                println("erro ao cadastrar Instrumento : ${t.message!!}")
//            }
//
//        })
    }

    fun cadastrarSociais(facebook: String, instagram: String, twitter: String) {
//        val apiMusicall = ConexaoApiMusicall.criar()
//
//
//        val sociais = Sociais(facebook, instagram, twitter)
//
//        apiMusicall.cadastrarRedesSociais(sociais).enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.code() == 200) {
//                    println("Cadastrou Redes sociais")
//                    val editor = preferencias.edit()
//                    editor.putInt("idRedeSocial", 1)
//                    editor.commit()
//                } else {
//                    println("Erro ao cadastrar Redes sociais: ${response.errorBody()!!}")
//                }
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                println("erro ao cadastrar sociais : ${t.message!!}")
//            }
//        })
    }
}
