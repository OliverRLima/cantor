package com.example.musicall.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicall.R
import com.example.musicall.conexaoApi.modelos.PublicacaoApi
import retrofit2.Call
import retrofit2.Response

class ManagerInvitesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screeninvites, container, false)
    }

    fun onResponse(call: Call<List<PublicacaoApi>>, response: Response<List<PublicacaoApi>>) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()

        response.body()?.forEach {
//            val titulo = "${it.nome} - ${it.instrumento} - ${it.dataAniversario} - ${it.cidade} - ${it.genero}"
//            val fragment = CardPublicacoes(titulo,it.publicacao)
//            fragmentTransaction?.add(R.id.ll_perfil, fragment)

        }
        fragmentTransaction?.commit()
    }

}