package com.example.musicall

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.Period
import java.util.*


class Info0 : AppCompatActivity() {

    var caledario = Calendar.getInstance()
    lateinit var data: EditText
    lateinit var dataBack: String
    var ano:Int = -1
    val ferramentas = Encapsulados()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info0)

        data = findViewById(R.id.et_data1)
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            caledario.set(Calendar.YEAR, year)
            caledario.set(Calendar.MONTH, monthOfYear)
            caledario.set(Calendar.DAY_OF_YEAR, dayOfMonth)
            ano = year
            if (monthOfYear == 12){
                data.setText(ferramentas.addZeros("${dayOfMonth}/${monthOfYear}/${year}"))
            } else {
                data.setText(ferramentas.addZeros("${dayOfMonth}/${monthOfYear + 1}/${year}"))
            }

            dataBack = "${monthOfYear+1}-${dayOfMonth}-${year}"
            dataBack = ferramentas.addZeros(dataBack)
        }

        data.setOnClickListener {
            DatePickerDialog(
                this@Info0, dateSetListener,
                caledario.get(Calendar.YEAR),
                caledario.get(Calendar.MONTH),
                caledario.get(Calendar.DAY_OF_YEAR)
            ).show()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun irInfo1(view: View) {
        var erro = false

        var dataTxt = data.text.toString()
        var anoAtual = LocalDate.now()
        var anoUser = LocalDate.of(ano,1,1)
        var idade = Period.between(anoUser,anoAtual)
        var estado: Spinner = findViewById(R.id.array_estado)
        val estadoTxt = estado.selectedItem.toString()
        var cidade: EditText = findViewById(R.id.et_cidade)
        val cidadeTxt = cidade.text.toString()

        if (dataTxt.isEmpty()) {
            data.error = "Insira uma data"
            erro = true
        }
        if (idade.years < 18 || idade.years > 80) {
            data.error = "Necessário ser maior de idade"
            erro = true
        }
        if (estadoTxt == "Estado") {
            Toast.makeText(applicationContext, "Escolha um estado", Toast.LENGTH_SHORT)
            erro = true
        }

        if (cidadeTxt.length < 3) {
            cidade.error = "Digite sua cidade"
            erro = true
        }

        if (!erro) {
            val info1 = Intent(this, ScreenInfo1::class.java)
            info1.putExtra("data", dataBack)
            info1.putExtra("estado", estadoTxt)
            info1.putExtra("cidade", cidadeTxt)
            println()
            startActivity(info1)
        }
    }

}

