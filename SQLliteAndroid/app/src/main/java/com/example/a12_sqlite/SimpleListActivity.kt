package com.example.a12_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.Cursor
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class SimpleListActivity : AppCompatActivity() {
    var db = DBAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list)
        // Creates local list view object
        var lstSimp = findViewById(R.id.lstSimp) as ListView

        // Create an ArrayAdapter object to populate the list
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, android.R.id.text1, cargaNombres()!!
        )

        // Assign adapter to the list view
        lstSimp.setAdapter(adapter)

        // Define on click method over list items
        lstSimp.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->

                // Retrieves text from position
                val itemValue = lstSimp.getItemAtPosition(position) as String

                // Show Toast
                Toast.makeText(
                    applicationContext,
                    "Position :$position  Nombre : $itemValue", Toast.LENGTH_LONG
                ).show()
            }
    }
    private fun cargaNombres(): Array<String?>? {
        // Open database
        db.open()

        // Retrieves all contacts
        val c: Cursor = db.getAllcontacts

        // Creates array of names
        val nombres = arrayOfNulls<String>(c.count)

        // Creates list of names
        var i = 0
        if (c.moveToFirst()) {
            do {
                nombres[i++] = c.getString(1)
            } while (c.moveToNext())
        }

        // Close database
        db.close()

        // Return list of names
        return nombres
    }
}