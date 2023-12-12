package com.example.a13_apiaccess
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import android.app.Activity

class ListTaskAdapter(val context: Activity,
                      val ids: Array<String?>,
                      val Tasks: Array<String?>,
                      val Fechas: Array<String?>): ArrayAdapter<String?>(context,0, ids){

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView: View = inflater.inflate(R.layout.multidata, null, true)

        val txtId = rowView.findViewById<View>(R.id.txtid) as TextView
        val txtTask = rowView.findViewById<View>(R.id.txtTask) as TextView
        val txtFecha = rowView.findViewById<View>(R.id.txtFecha) as TextView

        txtId.text = ids[position]
        txtTask.text = Tasks[position]
        txtFecha.text = Fechas[position]

        return rowView
    }
}