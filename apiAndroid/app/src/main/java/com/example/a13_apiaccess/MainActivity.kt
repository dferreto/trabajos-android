package com.example.a13_apiaccess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ListView
import com.example.a13_apiaccess.api.cls_Nota
import com.example.a13_apiaccess.api.cls_UsoApi
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private var token: String? = null
    private lateinit var lstComp: ListView

    // Create executor and handler for async tasks,
    // this is for use the api library in a background thread
    private val myExecutor = Executors.newSingleThreadExecutor()
    private val myHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // intenta obtener el token del usuario del local storage, sino llama a la ventana de registro
        val prefe = getSharedPreferences("appData", Context.MODE_PRIVATE)
        token = prefe.getString("token", "")
        lstComp = findViewById(R.id.lstComp)
        doLoadTasks()
        //valida la variable token

        //valida la variable token
        if (token.toString().trim { it <= ' ' }.length == 0) {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        } else {
            // TODO: llamar a la ventana de la aplicacion
        }
    }
    private fun doLoadTasks(): Array<cls_Nota?>? {
        val TAG = "Uso de API"
        var tareas: Array<cls_Nota?>? = null
        myExecutor.execute {
            try {

                Log.d(TAG, "llamando la clase de la API")
                //Apply data into the api library
                val api = cls_UsoApi()
                tareas = api.gettask(token)

            } catch (ex: Exception) {
                val trace = ex.stackTrace
                var i = 0
                while (i < trace.size) {
                    Log.d(TAG, trace[i].toString())
                    i++
                }
            }
            myHandler.post {
                // Declare arrays to store data
                val ids = arrayOfNulls<String>(tareas?.count() ?:0)
                val tasks = arrayOfNulls<String>(tareas?.count() ?:0)
                val fechas = arrayOfNulls<String>(tareas?.count() ?:0)

                // Fill arrays with data
                for (i in 0 until ids.size) {
                    ids[i] = tareas?.get(i)?.id
                    tasks[i] = tareas?.get(i)?.tarea
                    fechas[i] = tareas?.get(i)?.fecha
                }

                lstComp.adapter = ListTaskAdapter(this, ids, tasks, fechas)
            }
        }

        return tareas
    }
}