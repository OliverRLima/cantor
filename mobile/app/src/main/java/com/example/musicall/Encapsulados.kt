package com.example.musicall

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.conexaoApi.modelos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Encapsulados : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_api)
    }

    fun addZeros(original: String): String {
        var resultado = original
        var separador = "-"
        if (resultado.indexOf("-") < 0) separador = "/"

        if (!resultado.isEmpty() && resultado.substring(0,resultado.indexOf(separador)).length == 1) {
            resultado = "0$resultado"
            println("resultado : $resultado")
        }
        if (!resultado.isEmpty() && resultado.substring(resultado.indexOf(separador) + 1,resultado.lastIndexOf(separador)).length == 1)
        {
            resultado = resultado.substring(0, resultado.indexOf(separador) + 1) +
                    "0" + resultado.substring(resultado.indexOf(separador) + 1, resultado.length)
        }

        return resultado.substring(resultado.lastIndexOf(separador) + 1,resultado.length) +
                resultado.substring(2,resultado.lastIndexOf(separador)) + separador + resultado.substring(0, 2)
    }

    fun logar (view: View){
        val apiMusicall = ConexaoApiMusicall.criar()

        val usuario = Usuario("mariana.sampaio@gmail.com","senha123")
        apiMusicall.logar(usuario).enqueue(object : Callback<UsuarioApi>{
            override fun onResponse(call: Call<UsuarioApi>, response: Response<UsuarioApi>) {

                when {
                    response.code() == 200 -> {
                        val usuarioAuth = response.body()!!
                        Toast.makeText(baseContext, "Logado e completo", Toast.LENGTH_SHORT).show()
                    }
                    response.code() == 400 -> {

                    }
                    response.code() == 404 -> {

                    }
                    else -> {

                    }
                }
            }

            override fun onFailure(call: Call<UsuarioApi>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
