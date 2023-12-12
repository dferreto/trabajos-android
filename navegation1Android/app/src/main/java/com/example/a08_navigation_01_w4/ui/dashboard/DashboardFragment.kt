package com.example.a08_navigation_01_w4.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a08_navigation_01_w4.databinding.FragmentDashboardBinding
import com.example.a08_navigation_01_w4.R

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var text_dashboard = root.findViewById<TextView>(R.id.text_dashboard)
        text_dashboard.setText(R.string.message_dashboard)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}