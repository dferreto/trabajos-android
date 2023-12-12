package com.example.a08_navigation_01_w4.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a08_navigation_01_w4.R
import android.widget.TextView
import com.example.a08_navigation_01_w4.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentAboutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_about, container, false)

        var text_about = root.findViewById<TextView>(R.id.text_about)
        text_about.setText(R.string.message_about)

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}