package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.conexaoApi.modelos.UsuarioCadastradoApi
import com.example.musicall.conexaoApi.modelos.UsuarioCadastro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScreenRegistrar : AppCompatActivity() {
    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_registrar)
        preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)

    }

    fun registrar(view: View) {
        val api = ConexaoApiMusicall.criar()
        var erro = false
        var nome: EditText = findViewById(R.id.et_nome)
        var email: EditText = findViewById(R.id.et_email)
        var senha1: EditText = findViewById(R.id.et_senha)
        var senha2: EditText = findViewById(R.id.et_confirmar)

        if (nome.text.toString().length < 3) {
            nome.error = "Nome inválido"
            erro = true
        }

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

        val senha1Txt: String = senha1.text.toString()
        val senha2Txt: String = senha2.text.toString()

        if (!senha1Txt.equals(senha2Txt)) {
            senha2.error = "Senhas não coincidem"
            erro = true
        } else if (senha1Txt.length < 8) {
            senha2.error = "Senha curta: " + senha1Txt.length + " caracteres."
            erro = true
        }
        if (!erro) {
            println("validacoes ok, chamando cadastro")
            val usuarioCadastro = UsuarioCadastro(nome.text.toString(), emailTxt, senha1Txt)
            api.cadastrar(usuarioCadastro).enqueue(object : Callback<UsuarioCadastradoApi>{
                override fun onResponse(call: Call<UsuarioCadastradoApi>, response: Response<UsuarioCadastradoApi>) {
                    when {
                        response.code() == 201 -> {
                            val usuarioAuth = response.body()!!
                            val editor = preferencias.edit()
                            editor.putInt("idUsuario", usuarioAuth?.idUsuario)
                            editor.commit()
                            startActivity(Intent(baseContext, Info0::class.java))
                        }
                        response.code() == 400 -> {
                            Toast.makeText(baseContext, "Usuário já possui cadastro", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(baseContext, "Falha ao realizar o cadastro", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<UsuarioCadastradoApi>, t: Throwable) {
                    Toast.makeText(baseContext, "Erro ${t.message!!}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun entrar(view: View) {
        startActivity(Intent(baseContext, Login::class.java))
    }
}
