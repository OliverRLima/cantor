package com.example.musicall.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicall.R

class InvitesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val blankFragment: Fragment = CardPublicacoes()



        super.onViewCreated(view, savedInstanceState)
    }

}