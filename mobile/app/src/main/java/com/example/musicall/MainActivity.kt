package com.example.musicall

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.conexaoApi.modelos.Publicacao
import com.example.musicall.conexaoApi.modelos.PublicacaoApi
import com.example.musicall.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var preferencias: SharedPreferences
    lateinit var token:String
    var idUsuario = -1
    val api = ConexaoApiMusicall.criar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)
        token = preferencias.getString("token", null).toString()
        idUsuario = preferencias.getInt("idUsuario", -1)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val feedFragment = FeedFragment()
        val profileFragment = ProfileFragment(preferencias)
        val invitesFragment = ManagerInvitesFragment()
//        val invitesFragment2 = InvitesFragment2()
        val medalsFragment = MedalsFragment(preferencias)
        val settigsFragment = SettingsFragment()

        makecurrentFragment(feedFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_feed -> makecurrentFragment(feedFragment)
                R.id.ic_profile -> makecurrentFragment(profileFragment)
                R.id.ic_invite -> makecurrentFragment(invitesFragment)
                R.id.ic_medal -> makecurrentFragment(medalsFragment)
                R.id.ic_settings -> makecurrentFragment(settigsFragment)

            }
            true
        }
    }

    fun sair(view: View) {
        enDeslogar()
        finish()
    }

    fun alterarSenha(view: View){
        val intent = Intent(this, NewPassword::class.java).apply {
        }
        startActivity(intent)
    }

    fun deletarConta(view: View){
        api.deletarUsuario(idUsuario, token).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when {
                    response.code() == 200 -> {
                        Toast.makeText(baseContext, "Conta deletada :(", Toast.LENGTH_SHORT)
                        println("Conta deletada")
                        sair(view)
                    }
                    response.code() == 403 -> {
                        println("token inválido")
                        sair(view)
                    }
                    response.code() == 404 -> {
                        println("idUsuario não corresponde a nenhum usuário")
                    }
                    else -> {
                        println("falha ao tentar deletar um usuário")
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Erro ao tetnar deletar um usuário: ${t.message}")
            }

        })
    }

    fun irRecebidos(view: View) {
        val enviados: RelativeLayout = findViewById(R.id.enviados)
        val recebidos: RelativeLayout = findViewById(R.id.recebidos)

        enviados.visibility = View.INVISIBLE
        recebidos.visibility = View.VISIBLE
        println("FOI PARA ENVIADOS")
    }

    fun irEnviados(view: View) {
        val enviados: RelativeLayout = findViewById(R.id.enviados)
        val recebidos: RelativeLayout = findViewById(R.id.recebidos)

        recebidos.visibility = View.INVISIBLE
        enviados.visibility = View.VISIBLE
        println("FOI PARA RECEBIDOS")
    }

    fun irVerPerfil(view: View) {

        val intent = Intent(this, InfoUser::class.java).apply {
        }
        startActivity(intent)
    }

    private fun makecurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_menu, fragment)
            commit()
    }

    fun enDeslogar() {
        val preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.clear()
        editor.commit()
    }

    fun publicar(view: View) {
        val texto: EditText = findViewById(R.id.et_publicacao)
        val publicacao = Publicacao(texto.text.toString())

        api.fazerPublicacao(publicacao, token, idUsuario).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when {
                    response.code() == 200 -> {
                        Toast.makeText(baseContext, "Publicação realizada", Toast.LENGTH_SHORT)
                    }
                    response.code() == 400 -> {
                        Toast.makeText(baseContext, "Usuário não existe", Toast.LENGTH_SHORT)
                    }
                    response.code() == 403 -> {
                        enDeslogar()
                    }
                    else -> {
                        Toast.makeText(baseContext, "Falha ao realizar a publicação", Toast.LENGTH_SHORT)
                    }
                }

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(baseContext, "Erro ao realizar a publicacao", Toast.LENGTH_SHORT)
            }

        })
    }

    fun pesquisar(view: View) {
        println("publicação iniciou")
        val etBusca: EditText = findViewById(R.id.et_busca)
        val txtBusca = etBusca.text.toString()

        api.getPublicacoesFeed(idUsuario, txtBusca, token).enqueue(object : Callback<List<PublicacaoApi>>{
            override fun onResponse(call: Call<List<PublicacaoApi>>, response: Response<List<PublicacaoApi>>) {
                when {
                    response.code() == 200 -> {
                        val fragmentTransaction = supportFragmentManager?.beginTransaction()
                        response.body()?.forEach {
                            val titulo = "${it.nome} - ${it.instrumento} - ${it.idade} - ${it.estado} - ${it.genero}"
                            val fragment = CardPublicacoes(titulo, it.texto, false, it.idUsuario, idUsuario, token)
                            fragmentTransaction?.add(R.id.ll_feed, fragment)
                        }
                        fragmentTransaction?.commit()
                    }
                    response.code() == 403 -> {
                        enDeslogar()
                        finish()
                    }
                    response.code() == 404 -> {
                        Toast.makeText(baseContext, "Não foi encontrada publicações! Tente novamente", Toast.LENGTH_SHORT)
                    }
                    else -> {
                        Toast.makeText(baseContext, "Falha ao realizar a publicação", Toast.LENGTH_SHORT)
                    }
                }
            }

            override fun onFailure(call: Call<List<PublicacaoApi>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro ao pesquisar", Toast.LENGTH_SHORT)
            }

        })

    }

}