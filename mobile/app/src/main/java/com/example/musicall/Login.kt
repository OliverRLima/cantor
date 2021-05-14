package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.conexaoApi.modelos.Usuario
import com.example.musicall.conexaoApi.modelos.UsuarioApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    var erro: Boolean = false
    lateinit var preferencias: SharedPreferences

    lateinit var cadastro: Intent
    lateinit var feed: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        println("login iniciado")

        cadastro = Intent(this, Info0::class.java)
        feed = Intent(this, MainActivity::class.java)
        preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)
        val ultimoUsuario = preferencias.getInt("idUsuario", -1)
    }

    fun entrar(view: View) {
        var erro: Boolean = false

        var email: EditText = findViewById(R.id.edt_email)
        var senha: EditText = findViewById(R.id.edt_senha)

        val emailTxt: String = email.text.toString()

        if (emailTxt.indexOf(" ") > 0) {
            email.error = "Email incorreto: contém espaços"
            erro = true
        } else if (emailTxt.length < 8) {
            email.error = "Email incorreto: tamanho inválido"
            erro = true
        } else if (emailTxt.indexOf("@") < 0) {
            email.error = "Email incorreto: sem @"
            erro = true
        } else if (emailTxt.substring(emailTxt.indexOf("@")).indexOf(".") < 0) {
            email.error = "Email incorreto: não contém um '.com'"
            erro = true
        }
        val senhaTxt: String = senha.text.toString()
        if (senhaTxt.length < 8) {
            senha.error = "Senha curta: " + senhaTxt.length + " caracteres."
            erro = true
            println("entrou no if")

        } else {

            println("login iniciado")
            val api = ConexaoApiMusicall.criar()
            val usuario = Usuario(emailTxt, senhaTxt)

            println("login iniciado")
            api.logar(usuario).enqueue(object : Callback<UsuarioApi>{
                override fun onResponse(call: Call<UsuarioApi>, response: Response<UsuarioApi>) {

                    when {
                        response.code() == 200 -> {
                            val usuarioApi = response.body()!!
                            val editor = preferencias.edit()
                            editor.putInt("idUsuario", usuarioApi?.idUsuario)
                            editor.putString("token", String.format("%s %s", usuarioApi.tipo, usuarioApi.token))
                            editor.commit()
                            Toast.makeText(baseContext, "Logado e completo", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(feed)
                        }
                        response.code() == 400 -> {
                            Toast.makeText(baseContext, "email ou senha errados", Toast.LENGTH_SHORT).show()
                        }
                        response.code() == 404 -> {
                            Toast.makeText(baseContext, "Usuário não cadastrado", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(baseContext, "Erro ao realizar o login: ${response.errorBody()!!}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<UsuarioApi>, t: Throwable) {
                    println("Falha na chamada ${t.message!!}")
                }

            })
        }
    }

    fun registrar(view: View) {
        val intent = Intent(this, ScreenRegistrar::class.java).apply {
        }
        startActivity(intent)
    }

}