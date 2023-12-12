package com.example.a08_navigation_01_w4.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a08_navigation_01_w4.databinding.FragmentNotificationsBinding
import com.example.a08_navigation_01_w4.R

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var text_notifications = root.findViewById<TextView>(R.id.text_notifications)
        text_notifications.setText(R.string.message_notifications)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}