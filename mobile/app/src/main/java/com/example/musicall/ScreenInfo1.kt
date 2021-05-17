package com.example.musicall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class ScreenInfo1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info1)
    }

    fun irInfo2(view: View) {

        var erro = false

        val gen: Spinner = findViewById(R.id.sp_spinner1)
        val genTxt: String = gen.selectedItem.toString()

        val instr: Spinner = findViewById(R.id.sp_spinner2)
        val instrTxt: String = instr.selectedItem.toString()


        if (genTxt == "Gênero Musical") {
            Toast.makeText(baseContext,"Escolha um genero", Toast.LENGTH_SHORT)
            erro = true
        }

        if (instrTxt == "Instrumento") {
            Toast.makeText(baseContext,"Escolha um instrumento", Toast.LENGTH_SHORT)
            erro = true
        }

        if (!erro) {
            val info2 = Intent(this, Info2::class.java)

            info2.putExtra("data",intent.getStringExtra("data"))
            info2.putExtra("estado",intent.getStringExtra("estado"))
            info2.putExtra("cidade",intent.getStringExtra("cidade"))

            info2.putExtra("instrumento",instrTxt)
            info2.putExtra("genero",genTxt)

            startActivity(info2)
        }
    }
}