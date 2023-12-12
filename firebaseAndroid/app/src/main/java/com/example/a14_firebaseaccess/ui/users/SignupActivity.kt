package com.example.a14_firebaseaccess.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a14_firebaseaccess.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.example.a14_firebaseaccess.entities.Orders
import com.example.a14_firebaseaccess.entities.cls_Customer
import com.google.firebase.Firebase


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

import java.util.Date

class SignupActivity : AppCompatActivity() {
    var auth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()
    private val customerCollectionRef = Firebase.firestore.collection("Customers")
    private val orderCollectionRef = Firebase.firestore.collection("Orders")


    private lateinit var txtRNombre: EditText
    private lateinit var txtREmail: EditText
    private lateinit var txtRContra: EditText
    private lateinit var txtRreContra: EditText
    private lateinit var txtCustomerID: EditText
    private lateinit var btnRegistrarU: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        txtRNombre = findViewById(R.id.txtRNombre)
        txtREmail = findViewById(R.id.txtREmail)
        txtRContra = findViewById(R.id.txtRContra)
        txtRreContra = findViewById(R.id.txtRreContra)
        btnRegistrarU = findViewById(R.id.btnRegistrarU)

        txtCustomerID = findViewById(R.id.txtCustID)
        btnRegistrarU.setOnClickListener {
            registrarUsuario()
        }

    }
    private fun registrarUsuario() {
        val nombre = txtRNombre.text.toString()
        val email = txtREmail.text.toString()
        val contra = txtRContra.text.toString()
        val reContra = txtRreContra.text.toString()
        val cusID = txtCustomerID.text.toString()

        if (nombre.isEmpty() || email.isEmpty() || contra.isEmpty() || reContra.isEmpty() || cusID.isEmpty()) {
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show()
        } else {
            if (contra == reContra) {
                auth.createUserWithEmailAndPassword(email, contra)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val dt: Date = Date()
                            val user = hashMapOf(
                                "idemp" to task.result?.user?.uid,
                                "usuario" to nombre,
                                "email" to email,
                                "customerID" to cusID,
                                "ultAcceso" to dt.toString(),
                            )
                           val updating = updatePersonContactDetails(cusID, nombre)

                            db.collection("datosUsuarios")
                                .add(user)
                                .addOnSuccessListener { documentReference ->

                                    //Register the data into the local storage
                                    val prefe = this.getSharedPreferences("appData", MODE_PRIVATE)

                                    //Create editor object for write app data
                                    val editor = prefe.edit()

                                    if ( updating.equals(true)){

                                        retrieveOrder(cusID)
                                    }

                                    //Set editor fields with the new values
                                    editor.putString("email", email.toString())
                                    editor.putString("contra", contra.toString())
                                    editor.putString("customerID", cusID.toString())

                                    editor.putString("Shipvia", cusID.toString())
                                    editor.putString("ShipName", cusID.toString())
                                    editor.putString("ShipAddress", cusID.toString())
                                    editor.putString("ShipCity", cusID.toString())
                                    editor.putString("ShipRegion", cusID.toString())
                                    editor.putString("ShipPostalCode", cusID.toString())
                                    editor.putString("ShipCountry", cusID.toString())


                                    //Write app data
                                    editor.commit()

                                    Toast.makeText(this,"Usuario registrado correctamente",Toast.LENGTH_SHORT).show()

                                    Intent().let {
                                        setResult(RESULT_OK)
                                        finish()
                                    }
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this,"Error al registrar usuario",Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(this,"Error al registrar usuario",Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private  fun updatePersonContactDetails(customerID: String, nombre:String ) = CoroutineScope(Dispatchers.IO).launch {
        var isUpdateSuccessful = false
        try {
            val querySnapshot = customerCollectionRef
                .whereEqualTo("CustomerID", customerID)  // Asume que "id" es el campo en tus documentos.
                .get()
                .await()
            for(document in querySnapshot.documents) {
                val customer = document.toObject<cls_Customer>()
                if (customer?.CustomerID == customerID) {  // Asume que cls_Customer tiene una propiedad "id".
                    val documentReference = document.reference
                    documentReference.update(
                        mapOf(
                            "ContactName" to nombre,
                            "ContactTitle" to nombre
                        )
                    ).await()
                    isUpdateSuccessful = true
                    break  // Rompe el ciclo una vez que actualizas los detalles del contacto.
                }
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@SignupActivity, e.message, Toast.LENGTH_LONG).show()
            }
            false
        }
        isUpdateSuccessful  // Devuelve verdadero si la actualización fue exitosa.
    }

    private fun retrieveOrder(customerID: String) = CoroutineScope(Dispatchers.IO).launch {
        val sb = StringBuilder()
        try {
            val querySnapshot = orderCollectionRef
                .whereEqualTo("CustomerID", customerID)
                .get()
                .await()
            for(document in querySnapshot.documents) {
                val order = document.toObject<Orders>()
                sb.apply {
                    appendln("ShipAddress: ${order?.ShipAddress}")
                    appendln("ShipCity: ${order?.ShipCity}")
                    appendln("ShipCountry: ${order?.ShipCountry}")
                    appendln("ShipName: ${order?.ShipName}")
                    appendln("ShipPostalCode: ${order?.ShipPostalCode}")
                    appendln("ShipRegion: ${order?.ShipRegion ?: "NULL"}")
                    appendln("ShipVia: ${order?.ShipVia}")
                }
            }
            withContext(Dispatchers.Main) {

            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@SignupActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }

}


