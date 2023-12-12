package com.mata.a03_intents_w3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class ProcessActivity : AppCompatActivity() {
    private lateinit var lblCedula: EditText
    private lateinit var lblNombre: EditText
    private lateinit var lblSexo: EditText
    private lateinit var lblNacido: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)
        val id = Integer.parseInt(intent.extras!!.get("id")!!.toString())
        val nombre = intent.extras!!.get("nombre")!!.toString()
        val sexo = intent.extras!!.getChar("sexo")
        val nacido = intent.extras!!.get("nacido")!!.toString()

        lblCedula = findViewById(R.id.lblCedula)
        lblNombre = findViewById(R.id.lblNombre)
        lblSexo = findViewById(R.id.lblSexo)
        lblNacido = findViewById(R.id.lblNacido)

        lblCedula.setEnabled(false)
        lblNombre.setEnabled(false)
        lblSexo.setEnabled(false)
        lblNacido.setEnabled(false)

        lblCedula.setText(id.toString())
        lblNombre.setText(nombre)
        lblSexo.setText(if (sexo.toString() == "F") "Femenino" else "Masculino")
        lblNacido.setText(nacido)
    }
}