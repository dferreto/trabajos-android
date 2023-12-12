package com.example.a12_sqlite

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.content.Intent.ACTION_CALL
import android.content.Intent
import android.net.Uri

class ListAdapter(val context: Activity,
                  val ids: Array<String?>,
                  val Nombres: Array<String?>,
                  val Apellidos: Array<String?>,
                  val eMails: Array<String?>,
                  val Celulares: Array<String?>): ArrayAdapter<String?>(context,
    R.layout.activity_complex_list, ids){

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater

        val rowView: View = inflater.inflate(R.layout.multidata, null, true)
        val txtId = rowView.findViewById<View>(R.id.txtid) as TextView
        val txtNombre = rowView.findViewById<View>(R.id.txtNombre) as TextView
        val txtAppellidos = rowView.findViewById<View>(R.id.txtApellidos) as TextView
        val txtEmail = rowView.findViewById<View>(R.id.txtEmail) as TextView
        val txtCelular = rowView.findViewById<View>(R.id.txtCelular) as TextView

        txtId.text = ids[position]
        txtNombre.text = Nombres[position]
        txtAppellidos.text = Apellidos[position]
        txtEmail.text = eMails[position]
        txtCelular.text = Celulares[position]

        txtCelular.setOnClickListener {
            call(Celulares[position]!!)
        }

        txtEmail.setOnClickListener {
            email(eMails[position]!!)
        }

        return rowView
    }
    fun call(celular: String){
        context.startActivity(Intent(ACTION_CALL, Uri.parse("tel:$celular")))
    }
    fun email(email: String){
        val laotra = Intent(context, EmailActivity::class.java)
        laotra.putExtra("emailDestino", email)
        context.startActivity(laotra)
    }
}