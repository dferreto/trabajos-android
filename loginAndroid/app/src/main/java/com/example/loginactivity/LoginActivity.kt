package com.example.loginactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {
    private lateinit var btnAutenticar: Button
    private lateinit var txtEmail: EditText
    private lateinit var txtContra: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnAutenticar = findViewById(R.id.btnAutenticar)
        txtEmail = findViewById(R.id.txtEmail)
        txtContra = findViewById(R.id.txtContra)

        btnAutenticar.setOnClickListener {
            if (txtEmail.text.isNotEmpty() && txtContra.text.isNotEmpty()) {
                val email = txtEmail.text.toString()
                val contra = txtContra.text.toString()

                // create editor object for write app data
                val prefe = getSharedPreferences("appData", MODE_PRIVATE)
                val editor = prefe.edit()

                // set editor fields with the new values
                editor.putString("email", email)
                editor.putString("contra", contra)

                // write the authentication data into the local storage
                editor.apply()

                // call back to main activity
                finish()
            } else {
                showAlert(R.string.tit_Error, R.string.msg_Required)
            }
        }
    }
    private fun showAlert(titu: Int, mssg: Int) {
        val diagMessage = AlertDialog.Builder(this)
        diagMessage.setTitle(titu)
        diagMessage.setMessage(mssg)
        diagMessage.setPositiveButton(R.string.btn_Ok, null)

        val diagVentana: AlertDialog = diagMessage.create()
        diagVentana.show()
    }
}