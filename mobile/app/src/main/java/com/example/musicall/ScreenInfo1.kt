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

        var erro: Boolean = false

        val instr: Spinner = findViewById(R.id.sp_spinner1)
        val instrTxt: String = instr.selectedItem.toString()

        val gen: Spinner = findViewById(R.id.sp_spinner2)
        val genTxt: String = gen.selectedItem.toString()

        if (genTxt.length < 2) {
            Toast.makeText(baseContext,"Escolha um genero", Toast.LENGTH_SHORT)
            erro = true
        }
        if (instrTxt.length < 2) {
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