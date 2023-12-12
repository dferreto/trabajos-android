package com.example.a14_firebaseaccess.ui.Sales

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import com.example.a14_firebaseaccess.DBAdapter
import com.example.a14_firebaseaccess.R


class showSales : AppCompatActivity() {
    private lateinit var btnSimplex: Button
    private var db = DBAdapter(this)
    private lateinit var lstComp: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_sales)
        lstComp = findViewById(R.id.lstComp)

//        btnSimplex = findViewById(R.id.AddProd)
        btnSimplex.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, addSale::class.java)
            startActivity(intent)
        })


        cargaContactos()


    }
    fun cargaContactos() {
        // Open database
        db.open()

        // Retrieve all contacts
        val c: Cursor = db.getAllcontacts

        // Declare arrays to store data
        val Code = arrayOfNulls<String>(c.count)
        val producto = arrayOfNulls<String>(c.count)
        val precioUnitario = arrayOfNulls<String>(c.count)
        val cantidad = arrayOfNulls<String>(c.count)
        val Descuento = arrayOfNulls<String>(c.count)

        // Load data into arrays
        var i = 0
        if (c.moveToFirst()) {
            do {
                Code[i] = c.getString(0)
                producto[i] = c.getString(1)
                precioUnitario[i] = c.getString(2)
                cantidad[i] = c.getString(3)
                Descuento[i++] = c.getString(4)
            } while (c.moveToNext())
        }

        // Close database
        db.close()

        // Load data into ListView
//      lstComp.adapter = ListAdapter(this, Code, producto, precioUnitario, cantidad, Descuento)
    }
}