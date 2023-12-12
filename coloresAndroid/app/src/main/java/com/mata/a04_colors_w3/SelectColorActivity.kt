package com.mata.a04_colors_w3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button

class SelectColorActivity : AppCompatActivity() {
    private lateinit var btnAmarillo: Button
    private lateinit var btnAzul: Button
    private lateinit var btnIndigo: Button
    private lateinit var btnNaranja: Button
    private lateinit var btnRojo: Button
    private lateinit var btnVerde: Button
    private lateinit var btnVioleta: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_color)
        btnAmarillo = findViewById(R.id.btnAmarillo)
        btnAzul = findViewById(R.id.btnAzul)
        btnIndigo = findViewById(R.id.btnIndigo)
        btnNaranja = findViewById(R.id.btnNaranja)
        btnRojo = findViewById(R.id.btnRojo)
        btnVerde = findViewById(R.id.btnVerde)
        btnVioleta = findViewById(R.id.btnVioleta)

        val botonClick = View.OnClickListener {
                view -> when(view.id){
            R.id.btnRojo     -> setmiColor(getString(R.string.lblrojo)    , R.color.rojo)
            R.id.btnNaranja  -> setmiColor(getString(R.string.lblnaranja) , R.color.naranja)
            R.id.btnAmarillo -> setmiColor(getString(R.string.lblamarillo), R.color.amarillo)
            R.id.btnVerde    -> setmiColor(getString(R.string.lblverde)   , R.color.verde)
            R.id.btnAzul     -> setmiColor(getString(R.string.lblazul)    , R.color.azul)
            R.id.btnIndigo   -> setmiColor(getString(R.string.lblindigo)  , R.color.indigo)
            R.id.btnVioleta  -> setmiColor(getString(R.string.lblvioleta) , R.color.violeta)
        }
        }

        btnRojo.setOnClickListener(botonClick)
        btnNaranja.setOnClickListener(botonClick)
        btnAmarillo.setOnClickListener(botonClick)
        btnVerde.setOnClickListener(botonClick)
        btnAzul.setOnClickListener(botonClick)
        btnIndigo.setOnClickListener(botonClick)
        btnVioleta.setOnClickListener(botonClick)
    }
    private fun setmiColor(nomColor: String, valColor: Int ) {
        Intent().let { colorSelec ->
            colorSelec.putExtra(colorNombre, nomColor)
            colorSelec.putExtra(colorValor, valColor)
            setResult(Activity.RESULT_OK,colorSelec)
            finish()
        }
    }
}