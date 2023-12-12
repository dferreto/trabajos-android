package com.example.a12_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.Cursor
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import android.widget.ListView
import android.os.Build
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class ComplexListActivity : AppCompatActivity() {
    private var db = DBAdapter(this)
    private lateinit var lstComp: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complex_list)
        lstComp = findViewById(R.id.lstComp)

        lstComp.onItemClickListener =
            OnItemClickListener {
                    parent, view, position, id ->

                // Retrieves text from position
                val itemValue = lstComp.getItemAtPosition(position) as String

                //buscaPersona(itemValue.toInt())

//                val intent = Intent(applicationContext, changes::class.java)
//                startActivity(intent)
                val persona = buscaPersona(itemValue.toInt())

                    val intent = Intent(this, changes::class.java)
                    intent.putExtra("nombre", persona?.nombre)
                    intent.putExtra("apellidos", persona?.apellidos)
                    intent.putExtra("correoElectronico", persona?.correoElectronico)
                    intent.putExtra("numeroTelefonico", persona?.numeroTelefonico)
                    startActivity(intent)

            }

        // Call cargaContactos function
        //cargaContactos()
        //=================================================================
        // Call cargaContactos function with previous permission check
        requestPermission()
    }

    private fun requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED ->{
                    cargaContactos()
                }
                else -> requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }else{
            cargaContactos()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted -> if (isGranted){
        cargaContactos()
    }else{
        Toast.makeText(this, "Se requieren permisos", Toast.LENGTH_SHORT).show()
    }}
    data class Persona(val id: String, val nombre: String, val apellidos: String, val correoElectronico: String, val numeroTelefonico: String)
    fun buscaPersona(id: Int) : Persona?{
        // Open database
        db.open()

        // Retrieve contact by id
        val c: Cursor? = db.getContact_by_Id(id)
        var persona: Persona? = null

        if(c!!.count > 0){
            c.moveToFirst()

            persona = Persona(
                id = c.getString(0), // Asumiendo que la columna 0 es ID
                nombre = c.getString(1),
                apellidos = c.getString(2),
                correoElectronico = c.getString(3),
                numeroTelefonico = c.getString(4)
            )
            // Use Toast to show contact data
      //      Toast.makeText(
    //            applicationContext,
  //              "Nombre : ${c.getString(1)}\nApellidos : ${c.getString(2)}", Toast.LENGTH_LONG
 //           ).show()

        }


        // Close database
        db.close()

        return persona

    }

    fun cargaContactos() {
        // Open database
        db.open()

        // Retrieve all contacts
        val c: Cursor = db.getAllcontacts

        // Declare arrays to store data
        val ids = arrayOfNulls<String>(c.count)
        val nombres = arrayOfNulls<String>(c.count)
        val apellidos = arrayOfNulls<String>(c.count)
        val correos = arrayOfNulls<String>(c.count)
        val celulares = arrayOfNulls<String>(c.count)

        // Load data into arrays
        var i = 0
        if (c.moveToFirst()) {
            do {
                ids[i] = c.getString(0)
                nombres[i] = c.getString(1)
                apellidos[i] = c.getString(2)
                correos[i] = c.getString(3)
                celulares[i++] = c.getString(4)
            } while (c.moveToNext())
        }

        // Close database
        db.close()

        // Load data into ListView
        lstComp.adapter = ListAdapter(this, ids, nombres, apellidos, correos, celulares)
    }

}