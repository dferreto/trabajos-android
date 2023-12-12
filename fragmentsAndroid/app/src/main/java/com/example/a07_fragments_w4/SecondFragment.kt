package com.example.a07_fragments_w4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast



class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val f2_btnOk = view.findViewById<Button>(R.id.f2_btnOk)

        f2_btnOk.setOnClickListener{
            Toast.makeText(view.context,R.string.f2_Message,Toast.LENGTH_SHORT).show()
        }
        return view
    }

}