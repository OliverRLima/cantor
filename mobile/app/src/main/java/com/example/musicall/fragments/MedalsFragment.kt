package com.example.musicall.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicall.R
import com.example.musicall.conexaoApi.ConexaoApiMusicall

class MedalsFragment(preferencias: SharedPreferences) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val apiMusicall = ConexaoApiMusicall.criar()


    }
}