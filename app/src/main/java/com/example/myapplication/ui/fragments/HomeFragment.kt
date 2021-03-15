package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)

        binding.apply {
            lookup.setOnClickListener {
                if (editText.text.toString() == "") {
                    Toast.makeText(requireContext(), "Enter a valid text", Toast.LENGTH_SHORT)
                        .show()
                }
                val city = binding.editText.text
                val action = HomeFragmentDirections.actionHomeFragmentToListFragment(city.toString())
                findNavController().navigate(action)
            }
        }
    }
}