package com.example.musicall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.musicall.conexaoApi.ConexaoApiMusicall
import com.example.musicall.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val feedFragment = FeedFragment()
        val profileFragment = ProfileFragment()
        val invitesFragment = InvitesFragment()
//        val invitesFragment2 = InvitesFragment2()
        val medalsFragment = MedalsFragment()
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

    private fun makecurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_menu, fragment)
            commit()
        }
    fun enDeslogar() {
//        val api = ConexaoApiMusicall.criar()
//        api.logoff().enqueue(object : Callback<Void> {
//
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                val preferencias = getSharedPreferences("AUTENTICACAO", MODE_PRIVATE)
//                val editor = preferencias.edit()
//                editor.clear()
//                editor.commit()
//
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                println("erro na chamada : ${t.message!!}")
//            }
//        })

    }

}