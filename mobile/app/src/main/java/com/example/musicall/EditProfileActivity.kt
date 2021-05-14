package com.example.musicall

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class EditProfileActivity : AppCompatActivity() {
    /*var erro: Boolean = false*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_perfil)

    }


 /*   fun registrar(view: View) {
        var nome: EditText = findViewById(R.id.et_nome)
        var email: EditText = findViewById(R.id.et_email)
        var redes: EditText = findViewById(R.id.et_redes)
        var estilo: EditText = findViewById(R.id.et_estilo)
        var instrumento: EditText = findViewById(R.id.et_instrumento)
        var telefone: EditText = findViewById(R.id.et_telefone)

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

            *//*
            validação do campo do telefone
            var telefoneFiltrado: Int
            var telefoneString: String
            telefoneString = telefone.text.toString()
            telefoneString.replace(" ","")
            *//*



//
//        if (!erro) {
//
//            //aqui serão mandadas as alterações ao backend
//            Toast.makeText(applicationContext, "Dados editados com sucesso", Toast.LENGTH_LONG).show()
//            val intent = Intent(this, ProfileActivity::class.java).apply {
//            }
//            startActivity(intent)
//        }
//    }
//
//    fun cancelar(view: View) {
//        val intent = Intent(this, ProfileActivity::class.java).apply {
//        }
//        startActivity(intent)
//    }

    }*/
}

