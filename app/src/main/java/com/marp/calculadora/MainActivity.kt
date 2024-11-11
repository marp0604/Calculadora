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
    private var resultadoInicial = "0"

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
        tvResultado.text = resultadoInicial
    }

    fun printNumber(vista: View) {
        val textToBoton = (vista as TextView).text.toString()
        var esPunto = textToBoton == "."

        if (tvResultado.text.toString() == "0" && !esPunto)
            tvResultado.text = ""

        if (!esPunto || (!tvResultado.text.toString().contains(".")))
            tvResultado.text = tvResultado.text.toString() + textToBoton
    }

    fun borrar(vista: View) {

        when (vista.tag) {
            "A" -> {
                tvResultado.text = resultadoInicial
                operando1 = Double.NaN
                operando2 = Double.NaN
                operador = ""
            }

            "C" -> {
                val textAhora = tvResultado.text.toString()
                if (textAhora.isNotEmpty()) {
                    tvResultado.text = textAhora.dropLast(1)
                    if (tvResultado.text.isEmpty())
                        tvResultado.text = resultadoInicial
                }
            }
        }
    }

    fun calcular(vista: View){
        if (!operando1.isNaN() && !operando2.isNaN()) {
            val resultado = when (operador) {
                "+" -> operando1 + operando2
                "-" -> operando1 - operando2
                "*" -> operando1 * operando2
                "/" -> {
                    if (operando2 != 0.0) operando1 / operando2
                    else {
                        tvResultado.text = resultadoInicial
                        return
                    }
                }
                else -> Double.NaN
            }
            tvResultado.text = if (resultado.isNaN()) "Error" else resultado.toString()
        }
    }

    fun setOperador(vista: View) {
        if (!tvResultado.text.isNullOrEmpty()) {
            operando1 = tvResultado.text.toString().toDoubleOrNull() ?: Double.NaN
            operador = (vista as TextView).text.toString()
            tvResultado.text = resultadoInicial
        }
    }

    fun setResultado(vista: View) {
        calcular(vista)
        if (!tvResultado.text.isNullOrEmpty()) {
            operando2 = tvResultado.text.toString().toDoubleOrNull() ?: Double.NaN

        }
    }
}