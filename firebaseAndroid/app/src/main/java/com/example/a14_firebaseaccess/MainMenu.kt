package com.example.a14_firebaseaccess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.a14_firebaseaccess.entities.cls_Category
import com.example.a14_firebaseaccess.ui.Sales.showSales
import com.example.a14_firebaseaccess.ui.categories.CategoryAdapter
import com.google.firebase.firestore.FirebaseFirestore

class MainMenu : AppCompatActivity() {
    private lateinit var btnSimplex: Button
    private lateinit var btnComplex: Button
    var db = FirebaseFirestore.getInstance()
    var TAG = "MataTestingApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        btnSimplex = findViewById(R.id.btnSimplex)
        btnComplex = findViewById(R.id.btnComplex)
        btnSimplex.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, showSales::class.java)
            startActivity(intent)
        })
        //Traer las categorias
//        btnComplex.setOnClickListener(View.OnClickListener {
//            val intent = Intent(applicationContext, ComplexListActivity::class.java)
//            startActivity(intent)
//        })
    }

    private fun obtenerDatos() {
        //Toast.makeText(this,"Esperando hacer algo importante", Toast.LENGTH_LONG).show()
        var coleccion: ArrayList<cls_Category?> = ArrayList()
        var listaView: ListView = findViewById(R.id.lstCategories)
        db.collection("Categories").orderBy("CategoryID")
            .get()
            .addOnCompleteListener { docc ->
                if (docc.isSuccessful) {
                    for (document in docc.result!!) {
                        Log.d(TAG, document.id + " => " + document.data)
                        var datos: cls_Category = cls_Category(document.data["CategoryID"].toString().toInt(),
                            document.data["CategoryName"].toString(),
                            document.data["Description"].toString(),
                            document.data["urlImage"].toString())
                        coleccion.add(datos)
                    }
                    var adapter: CategoryAdapter = CategoryAdapter(this, coleccion)
                    listaView.adapter =adapter
                } else {
                    Log.w(TAG, "Error getting documents.", docc.exception)
                }
            }
    }
}