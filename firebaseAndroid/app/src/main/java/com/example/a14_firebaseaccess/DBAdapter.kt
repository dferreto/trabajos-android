package com.example.a14_firebaseaccess

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBAdapter(val context: Context) {
    // Creates datastore
    var dbHelper: DatabaseHelper
    var db: SQLiteDatabase? = null

    // Opens datastore
    @Throws(SQLException::class)
    fun open(): DBAdapter {
        db = dbHelper.writableDatabase
        return this
    }

    // Close the datastore
    fun close() {
        dbHelper.close()
    }

    // ------------------------------------------
    // Commons Conctactos table methods
    // ------------------------------------------
    // Insert new contact
    fun insContact(Cod: String?, Product: String?, PrecioUni: String?, Cantidad: String?, descuento: String?, SubTotal: String?): Long {
        val initialValues = ContentValues()
        initialValues.put(Code, Cod)
        initialValues.put(producto, Product)
        initialValues.put(precioUnitario, PrecioUni)
        initialValues.put(cantidad, Cantidad)
        initialValues.put(Descuento, descuento)
        initialValues.put(Subtotal, SubTotal)
        return db!!.insert(DB_Tabla, null, initialValues)
    }

    // Update a contact
    fun updContact(cod: String?, Prod: String?, PrecioUni: String?, Cantidad: String?, descuento: String?, SubTotal: String?): Boolean {
        val args = ContentValues()
        args.put(Code, cod)
        args.put(producto, Prod)
        args.put(precioUnitario, PrecioUni)
        args.put(cantidad, Cantidad)
        args.put(Descuento, descuento)
        args.put(Subtotal, SubTotal)
        return db!!.update(
            DB_Tabla,
            args,
            "$Code=$cod",
            null
        ) > 0
    }

    // Delete a contact
    fun delContact(id: Long): Boolean {
        return db!!.delete(
            DB_Tabla,
            "$Code=$id",
            null
        ) > 0
    }

    // Retrieves all contacts
    val getAllcontacts: Cursor
        get() = db!!.query(
            DB_Tabla,
            arrayOf(
                Code,
                producto,
                precioUnitario,
                cantidad,
                Descuento,
                Subtotal
            ),
            null,
            null,
            null,
            null,
            null,
            null
        )

    // Retrieves a specific contact by yours id
    fun getContact_by_Id(id: Int): Cursor? {
        val mCursor = db!!.query(
            true,
            DB_Tabla,
            arrayOf(
                Code,
                producto,
                precioUnitario,
                cantidad,
                Descuento,
                Subtotal
            ),
            "$Code=$id",
            null,
            null,
            null,
            null,
            null
        )
        mCursor?.moveToFirst()
        return mCursor
    }

    // Inner class to create and update the datastore
    inner class DatabaseHelper(context: Context?) :
        SQLiteOpenHelper(
            context,
            DB_Nombre,
            null,
            DB_Version
        ) {
        override fun onCreate(db: SQLiteDatabase) {
            try {
                // Try to create the datastore
                db.execSQL(creaBase)
            } catch (e: SQLException) {
                // Print in logcat the error found
                Log.e(TAG, e.message?:"")
            }
        }

        override fun onUpgrade(
            db: SQLiteDatabase,
            oldVersion: Int,
            newVersion: Int
        ) {
            // Print in logcat the warning about the datastore changes
            Log.w(
                TAG,
                "Actualizando base de datos de la versión " + oldVersion + " a la "
                        + newVersion + ", los datos antiguos serán eliminados"
            )

            // Drop contactos table
            db.execSQL("DROP TABLE IF EXISTS orderDetails")

            // Call the constructor method to recreate the datastore
            onCreate(db)
        }
    }

    companion object {
        // Registra el nombre de la aplicación para el log
        const val TAG = "Datos"

        // Datos generales del almacenamiento local
        const val DB_Nombre = "LocalDB"
        const val DB_Version = 1
        const val DB_Tabla = "orderDetails"

        // Definición de los campos de la tabla detalles
        const val Code = "Code"
        const val producto = "producto"
        const val precioUnitario = "precioUnitario"
        const val cantidad = "Cantidad"
        const val Descuento = "Descuento"
        const val Subtotal = "SubTotal"

        // Construye comando SQL para crear la tabla detalles
        const val creaBase = "create table orderDetails(" +
                "Code text primary key autoincrement, " +
                "producto text not null, " +
                "precioUnitario text not null, " +
                "Cantidad text not null, " +
                "Descuento text not null, " +
                "SubTotal text not null);"
    }

    // --------------------------------------
    // Commons database methods
    // --------------------------------------
    // Class constructor
    init {
        // Creates a new instance of the database helper
        dbHelper = DatabaseHelper(context)
    }
}