package com.example.musicall

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.conexaoApi.modelos.UsuarioSenha
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewPassword : AppCompatActivity() {

    lateinit var preferencias: SharedPreferences
    lateinit var token:String
    var idUsuario = -1
    val api = ConexaoApiMusicall.criar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)
        token = preferencias.getString("token", null).toString()
        idUsuario = preferencias.getInt("idUsuario", -1)
    }

    fun alterar(view: View) {
        val etSenha: EditText = findViewById(R.id.et_senhaNova)
        val etSenhaConfirmacao: EditText = findViewById(R.id.et_confirmarSenhaNova)
        val usuarioSenha = UsuarioSenha(etSenha.text.toString())

        api.alterarSenha(idUsuario, usuarioSenha, token).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                when{
                    response.code() == 200 -> {
                        println("senha alterada")
                    }
                    response.code() == 400 -> {
                        println("idUsuario não corresponde a nenhum usuário")
                    }
                    response.code() == 403 -> {
                        println("token invalido")
                        val editor = preferencias.edit()
                        editor.clear()
                        editor.commit()
                        finish()
                    }
                    else -> {
                        println("falha ao tentar alterar a senha do usuário")
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Erro ao tentar alterar a senha do usuário: ${t.message}")
            }

        })

    }

}