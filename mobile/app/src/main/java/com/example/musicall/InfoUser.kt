package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.conexaoApi.modelos.Dados
import com.example.musicall.conexaoApi.modelos.DadosApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoUser : AppCompatActivity() {

    lateinit var preferencias: SharedPreferences
    lateinit var token:String
    var idUsuario = -1

    lateinit var data:String
    lateinit var genero:String
    lateinit var instrumento:String
    lateinit var estado:String
    lateinit var cidade:String
    lateinit var facebook:String
    lateinit var instagram:String
    lateinit var telefone:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)

        preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)
        token = preferencias.getString("token", null).toString()
        idUsuario = preferencias.getInt("idUsuario", -1)

        val apiMusicall = ConexaoApiMusicall.criar()
        apiMusicall.getDadosUsuario(idUsuario, token).enqueue(object : Callback<DadosApi>{
            override fun onResponse(call: Call<DadosApi>, response: Response<DadosApi>) {
                when {
                    response.code() == 200 -> {
                        val dados = response.body()!!
                        carregarObjetos(dados)

                        var tvNome: TextView = findViewById(R.id.tv_nome)
                        var tvData: TextView = findViewById(R.id.tv_data)
                        var tvGenero: TextView = findViewById(R.id.tv_genero)
                        var tvInstrumento: TextView = findViewById(R.id.tv_instrumento)
                        var tvEstado: TextView = findViewById(R.id.tv_estado)
                        var tvCidade: TextView = findViewById(R.id.tv_cidade)
                        var tvFacebook: TextView = findViewById(R.id.tv_facebook)
                        var tvInstagram: TextView = findViewById(R.id.tv_instagram)
                        var tvCelular: TextView = findViewById(R.id.tv_celular)

                        tvNome.text = dados.nome
                        tvData.text = dados.dataAniversario
                        tvGenero.text = dados.generoMusical
                        tvInstrumento.text = dados.instrumento
                        tvEstado.text = dados.estado
                        tvCidade.text = dados.cidade
                        tvFacebook.text = dados.facebook
                        tvInstagram.text = dados.instagram
                        tvCelular.text = dados.telefone

                    }
                    response.code() == 403 -> {
                        val editor = preferencias.edit()
                        editor.clear()
                        editor.commit()
                        finish()
                    }
                    response.code() == 404 -> {
                        println("idUsuario é inválido")
                    }
                    else -> {
                        println("Falha ao carregar as informações do usuário")
                    }
                }
            }

            override fun onFailure(call: Call<DadosApi>, t: Throwable) {
                println("Erro ao pegar as publicações do usuário: ${t.message!!}")
            }

        })
    }

    private fun carregarObjetos(dados: DadosApi) {
        data = dados.dataAniversario
        genero = dados.generoMusical
        instrumento = dados.instrumento
        estado = dados.estado
        cidade = dados.cidade
        facebook = dados.facebook
        instagram = dados.instagram
        telefone = dados.telefone
    }

    fun irEditarPerfil(view: View) {
        val intent = Intent(this, EditProfileActivity::class.java).apply {
        }
        intent.putExtra("data", data)
        intent.putExtra("cidade", cidade)
        intent.putExtra("facebook", facebook)
        intent.putExtra("instagram", instagram)
        intent.putExtra("telefone", telefone)
        startActivity(intent)
    }
}