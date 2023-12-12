package com.example.a12_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var db = DBAdapter(this)
    val TAG = "SQLite Demo"
    private lateinit var btnSimplex: Button
    private lateinit var btnComplex: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //preCargarDatos()
        btnSimplex = findViewById(R.id.btnSimplex)
        btnComplex = findViewById(R.id.btnComplex)
        btnSimplex.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, SimpleListActivity::class.java)
            startActivity(intent)
        })
        btnComplex.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, ComplexListActivity::class.java)
            startActivity(intent)
        })
    }
    private fun preCargarDatos() {
        var id: Long

        // Open database
        db.open()

        // Insert data collection
        id = db.insContact("Jhon", "Allen Valerin", "jhallenva@est.utn.ac.cr", 86852507)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Eduard Andres", "Arias Quiros", "edariasqu@est.utn.ac.cr", 83872404)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Christopher", "Carmona Elizondo", "chcarmonael@est.utn.ac.cr", 63627184)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("William Jose", "Cubero Navarro", "wicuberona@est.utn.ac.cr", 62363839)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("William Jesus", "Delgado Vargas", "wdelgadov@est.utn.ac.cr", 71751788)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Dinier Andres", "Ferreto Moraga", "diferretomo@est.utn.ac.cr", 60329853)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Sebastian Alberto", "Mata Ortega", "semataoe@est.utn.ac.cr", 87244352)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Jose Andres", "Nuñez Guerrero", "onunezgu@est.utn.ac.cr", 62602552)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Kenneth Josue", "Piedra Vargas", "kepiedravar@est.utn.ac.cr", 61277982)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Alejandro Jose", "Rodriguez Jaen", "alrodriguezja@est.utn.ac.cr", 84452876)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Melanie", "Rodriguez Jimenez", "merodriguezji@est.utn.ac.cr", 86706999)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Emmanuel de Jesus", "Rodriguez Solano", "emrodriguezso@est.utn.ac.cr", 72149100)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Angelica Marian", "Siles Diaz", "emrodriguezso@est.utn.ac.cr", 60381539)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Jocksan Lirion", "Vargas Rodriguez", "jovargasro@est.utn.ac.cr", 85136696)
        Log.v(TAG, "Registrando usuario [ $id ]")

        id = db.insContact("Marvin Adrian", "Vasquez Rodriguez", "mavasquezro@est.utn.ac.cr", 61209487)
        Log.v(TAG, "Registrando usuario [ $id ]")

        // Close database
        db.close()
    }
}