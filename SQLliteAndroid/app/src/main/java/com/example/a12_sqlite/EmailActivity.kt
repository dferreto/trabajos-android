package com.example.a12_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        // retrieves email as parameter
        val email = intent.extras!!.get("emailDestino")!!.toString()

        // create instances from the view
        var txtdestino: EditText = findViewById(R.id.txtDestino)
        var txtasunto : EditText = findViewById(R.id.txtAsunto)
        var txtmensaje: EditText = findViewById(R.id.txtMensaje)
        var btnenviar : Button = findViewById(R.id.btnEnviar)

        // update the view objects
        txtdestino.setText(email)
        txtasunto.setText("Solicito ayuda")
        txtasunto.selectAll()

        // set the click event for the button
        btnenviar.setOnClickListener {
            var aplicar = true

            // Validate the fields is not empty
            if(txtdestino.text.toString().isEmpty()){
                Toast.makeText(this,R.string.valdestino, Toast.LENGTH_SHORT).show()
                txtdestino.requestFocus()
                aplicar = false
            }else if(txtasunto.text.toString().isEmpty()){
                Toast.makeText(this,R.string.valasunto, Toast.LENGTH_SHORT).show()
                txtasunto.requestFocus()
                aplicar = false
            }else if(txtmensaje.text.toString().isEmpty()){
                Toast.makeText(this,R.string.valmensaje, Toast.LENGTH_SHORT).show()
                txtmensaje.requestFocus()
                aplicar = false
            }

            // Calls the device's messaging process
            if(aplicar){
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(txtdestino.text.toString()))
                intent.putExtra(Intent.EXTRA_SUBJECT, txtasunto.text.toString())
                intent.putExtra(Intent.EXTRA_TEXT, txtmensaje.text.toString())
                intent.type = "message/rfc822"
                startActivity(Intent.createChooser(intent, "Elije un cliente de correo:"))
            }
        }
    }
}