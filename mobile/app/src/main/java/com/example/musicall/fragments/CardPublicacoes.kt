package com.example.musicall.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.musicall.R
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CardPublicacoes(txtTitulo: String,txtConteudo: String, boolProprio: Boolean, idCard: Int, idUsuario: Int, token: String) : Fragment() {

    val titulo:String = txtTitulo
    val conteudo:String = txtConteudo
    val proprio: Boolean = boolProprio
    val idCard:Int = idCard
    val idUsuario:Int = idUsuario
    val token:String = token

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_publicacao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiMusicall = ConexaoApiMusicall.criar()
        val tvTitulo: TextView= view.findViewById(R.id.publicacao_titulo)
        val tvTexto: TextView= view.findViewById(R.id.publicacao_texto)

        val ibLixo: ImageButton = view.findViewById(R.id.ib_excluir)
        val ibInvite: ImageButton = view.findViewById(R.id.ib_invite)

        ibLixo.setOnClickListener{
            apiMusicall.deletarPublicacao(idCard, token).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    when {
                        response.code() == 200 -> {
                            println("publicacao deletada")
                        }
                        response.code() == 400 -> {
                            println("Id da publicação não corresponde a nenhuma publicação")
                        }
                        response.code() == 403 -> {
                            println("Token inválido")
                        }
                        else -> {
                            println("Falha ao tentar excluir uma publicação")
                        }
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println("Erro ao tentar excluir uma publicação: ${t.message}")
                }

            })
        }

        ibInvite.setOnClickListener {
            apiMusicall.enviarConvite(idUsuario, idCard, token).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    when{
                        response.code() == 200 -> {
                            println("Convite enviado!")
                        }
                        response.code() == 400 -> {
                            println("Convite já foi enviado")
                        }
                        response.code() == 403 -> {
                            println("Token inválido")

                        }
                        else -> {
                            println("Falha ao tentar enviar um convite")
                        }
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println("Erro ao tentar enviar convite: ${t.message}")
                }

            })
        }
        tvTitulo.text = titulo
        tvTexto.text = conteudo
        if (proprio){
            ibLixo.visibility = View.VISIBLE
            ibInvite.visibility = View.INVISIBLE
        } else{
            ibLixo.visibility = View.INVISIBLE
            ibInvite.visibility = View.VISIBLE
        }
    }

}