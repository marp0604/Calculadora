package com.marp.calculadora

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvResultado: TextView
    private var operando1: Double = Double.NaN
    private var operando2: Double = Double.NaN
    private var operador: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvResultado = findViewById(R.id.tvResultado)
    }

    fun printNumber(vista: View){
        val textToBoton = (vista as TextView).text.toString()
        var esPunto = textToBoton == "."

        if (tvResultado.text.toString() == "0" && !esPunto)
            tvResultado.text = ""

        if(!esPunto || (!tvResultado.text.toString().contains(".")))
        tvResultado.text = tvResultado.text.toString() + textToBoton
    }

    fun borrar(vista: View){

        if(vista.tag.equals("A")){
            tvResultado.text = "0"
        }

    }

    fun setOperador(vista: View){

    }

    fun setResultado(vista: View){

    }

}