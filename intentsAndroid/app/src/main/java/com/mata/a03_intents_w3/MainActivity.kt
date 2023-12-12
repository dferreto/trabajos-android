package com.mata.a03_intents_w3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var txtCedula: EditText
    private lateinit var txtNombre: EditText
    private lateinit var fecNacido: EditText
    private lateinit var rbtFemenino: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtCedula   = findViewById(R.id.txtCedula)
        txtNombre   = findViewById(R.id.txtNombre)
        fecNacido   = findViewById(R.id.fecNacido)
        rbtFemenino = findViewById(R.id.rbtFemenino)
    }
    fun ejecutar(view: View) {
        var aplicar = true
        if (txtCedula.getText().toString().trim({ it <= ' ' }).length == 0) {
            Toast.makeText(this, "Id requerido", Toast.LENGTH_SHORT).show()
            txtCedula.requestFocus()
            aplicar = false
        }

        if (txtNombre.getText().toString().trim({ it <= ' ' }).length == 0 && aplicar) {
            Toast.makeText(this, "Nombre requerido", Toast.LENGTH_SHORT).show()
            txtNombre.requestFocus()
            aplicar = false
        }

        if (fecNacido.getText().toString().trim({ it <= ' ' }).length == 0 && aplicar) {
            Toast.makeText(this, "Fecha requerida", Toast.LENGTH_SHORT).show()
            fecNacido.requestFocus()
            aplicar = false
        }

        if (aplicar) {
            //Invoca otra ventana
            val laotra = Intent(this, ProcessActivity::class.java)
            var sexo = 'F'

            if (rbtFemenino.isChecked) {
                sexo = 'F'
            } else {
                sexo = 'M'
            }

            laotra.putExtra("id", txtCedula.getText())
            laotra.putExtra("nombre", txtNombre.getText())
            laotra.putExtra("sexo", sexo)
            laotra.putExtra("nacido", fecNacido.getText())

            startActivity(laotra)

        }//fin del if de aplicar
    }//fin del método ejecutar
}