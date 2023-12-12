package com.example.a12_sqlite

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
    fun insContact(nom: String?, apes: String?, email: String?, cel: Long): Long {
        val initialValues = ContentValues()
        initialValues.put(Nombre, nom)
        initialValues.put(Apellidos, apes)
        initialValues.put(eMail, email)
        initialValues.put(Celular, cel)
        return db!!.insert(DB_Tabla, null, initialValues)
    }

    // Update a contact
    fun updContact(id: Long, nom: String?, apes: String?, email: String?, cel: Long): Boolean {
        val args = ContentValues()
        args.put(Nombre, nom)
        args.put(Apellidos, apes)
        args.put(eMail, email)
        args.put(Celular, cel)
        return db!!.update(
            DB_Tabla,
            args,
            "$Iden=$id",
            null
        ) > 0
    }

    // Delete a contact
    fun delContact(id: Long): Boolean {
        return db!!.delete(
            DB_Tabla,
            "$Iden=$id",
            null
        ) > 0
    }

    // Retrieves all contacts
    val getAllcontacts: Cursor
        get() = db!!.query(
            DB_Tabla,
            arrayOf(
                Iden,
                Nombre,
                Apellidos,
                eMail,
                Celular
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
                Iden,
                Nombre,
                Apellidos,
                eMail,
                Celular
            ),
            "$Iden=$id",
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
            db.execSQL("DROP TABLE IF EXISTS contactos")

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
        const val DB_Tabla = "contactos"

        // Definición de los campos de la tabla contactos
        const val Iden = "iden"
        const val Nombre = "nombre"
        const val Apellidos = "apellidos"
        const val eMail = "email"
        const val Celular = "celular"

        // Construye comando SQL para crear la tabla contactos
        const val creaBase = "create table contactos(" +
                "iden integer primary key autoincrement, " +
                "nombre text not null, " +
                "apellidos text not null, " +
                "email text not null, " +
                "celular integer not null);"
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