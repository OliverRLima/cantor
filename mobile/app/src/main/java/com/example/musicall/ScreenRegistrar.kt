package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.conexaoApi.modelos.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScreenRegistrar : AppCompatActivity() {
    val ferramentas = Encapsulados()
    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_registrar)
        preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)

    }

    fun registrar(view: View) {
        val api = ConexaoApiMusicall.criar()
        var erro: Boolean = false
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
//            val usuario = Usuario("",emailTxt, senha1Txt)
//            var codigo = 0
//            val api = ConexaoApiMusicall.criar()
//            api.veri(emailTxt).enqueue(object : Callback<Void> {
//                override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                    if (response.code() == 200) {
//                        println("email não existe, executando cadastro")
//
//                        api.cadastrar(usuario).enqueue(object : Callback<Void> {
//                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                                if (response.code() == 201) {
//                                    println("cadastrado com sucesso, então logando")
//
//                                    api.logar(usuario).enqueue(object : Callback<UsuarioAutenticado> {
//                                        override fun onResponse(call: Call<UsuarioAutenticado>,response: Response<UsuarioAutenticado>) {
//                                            if (response.code() == 200) {
//                                                val usuarioAuth = response.body()!!
//                                                println("login feito, redirecionando para cadastro adicional")
//                                                val editor = preferencias.edit()
//                                                editor.putInt("idUsuario", usuarioAuth?.idUsuario)
//                                                editor.putInt("idInfoUsuario", usuarioAuth?.idInfoUsuario)
//                                                editor.putInt("idRedeSocial", usuarioAuth?.idRedeSocial)
//                                                editor.commit()
//                                                startActivity(Intent(baseContext, Info0::class.java))
//                                            } else if (response.code() == 400) {
//                                                println("Já há um usuário logado")
//                                            }
//                                        }
//                                        override fun onFailure(call: Call<UsuarioAutenticado>, t: Throwable) {
//                                            println("Falha na chamada ${t.message!!}")
//                                            Toast.makeText(baseContext, "Falha ${t.message!!}", Toast.LENGTH_SHORT).show()
//                                            println("${t.message!!}")
//                                        }
//
//                                    })
//
//
//                                } else {
//                                    println("Erro ao cadastrar usuario ): ${response.errorBody()!!} = dados inválidos")
//                                }
//                            }
//                            override fun onFailure(call: Call<Void>, t: Throwable) {
//                                println("Falha na chamada de cadastrar usuario ${t.message!!}")
//                            }
//                        })
//                    } else {
//                        codigo = response.code()
//                        Toast.makeText(baseContext, "Este email já existe", Toast.LENGTH_SHORT).show()
//                        println("email existe! status :${codigo}")
//                    }
//                }
//
//                override fun onFailure(call: Call<Void>, t: Throwable) {
//                    println("erro na chamada : ${t.message!!}")
//                }
//            })

        }
    }

    fun entrar(view: View) {
        startActivity(Intent(baseContext, Login::class.java))
    }
}
