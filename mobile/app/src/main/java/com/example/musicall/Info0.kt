package com.example.musicall

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class Info0 : AppCompatActivity() {

    var caledario = Calendar.getInstance()
    lateinit var data: EditText
    lateinit var dataBack: String
    val ferramentas = Encapsulados()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info0)

        data = findViewById(R.id.et_data)
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {

                caledario.set(Calendar.YEAR, year)
                caledario.set(Calendar.MONTH, monthOfYear)
                caledario.set(Calendar.DAY_OF_YEAR, dayOfMonth)

                data.setText(ferramentas.addZeros("${dayOfMonth}/${monthOfYear + 1}/${year}"))
                dataBack = "${monthOfYear+1}-${dayOfMonth}-${year}"
                dataBack = ferramentas.addZeros(dataBack)
            }
        }

        data.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

                DatePickerDialog(
                    this@Info0, dateSetListener,
                    caledario.get(Calendar.YEAR),
                    caledario.get(Calendar.MONTH),
                    caledario.get(Calendar.DAY_OF_YEAR)
                ).show()
            }
        })
    }


    fun irInfo1(view: View) {
        var erro: Boolean = false

        var dataTxt = data.text.toString()

        var estado: Spinner = findViewById(R.id.array_estado)
        val estadoTxt = estado.selectedItem.toString()
        var cidade: EditText = findViewById(R.id.et_cidade)
        val cidadeTxt = cidade.text.toString()

        if (dataTxt.isEmpty()) {
            data.error = "Insira uma data"
            erro = true
        }
        if (estadoTxt.length < 3) {
            Toast.makeText(baseContext, "Escolha um estado", Toast.LENGTH_SHORT)
            erro = true
        }

        if (cidadeTxt.length < 3) {
            cidade.error = "Digite seu cidade"
            erro = true
        }

        if (!erro) {
            val info1 = Intent(this, ScreenInfo1::class.java)
            println("dataTxt do info0 " +   dataTxt)
            println("NOVA STRING ${dataBack}")

            info1.putExtra("data", dataBack)
            info1.putExtra("estado", estadoTxt)
            info1.putExtra("cidade", cidadeTxt)
            println()
            startActivity(info1)
        }
    }

}

