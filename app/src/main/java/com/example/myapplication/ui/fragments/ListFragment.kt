package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.model.Weather
import com.example.myapplication.ui.adapter.WeatherListAdapter
import com.example.myapplication.utils.DataState
import com.example.myapplication.viewmodel.WeatherViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), WeatherListAdapter.OnClickListener {

    private val viewModel: WeatherViewmodel by viewModels()
    private val args: ListFragmentArgs by navArgs()
    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)

        (activity as AppCompatActivity).supportActionBar?.title = args.city
        val weatherListAdapter = WeatherListAdapter(this)
        binding.apply {
            recycler.apply {
                adapter = weatherListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.getWeatherByCity(args.city)

        viewModel.weatherData.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    displayProgressBar(false)
                    weatherListAdapter.submitList(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.INVISIBLE
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Unknown Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClicked(weather: Weather) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(weather, args.city)
        findNavController().navigate(action)
    }
}
