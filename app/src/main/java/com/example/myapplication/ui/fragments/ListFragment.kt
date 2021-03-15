package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.ui.adapter.WeatherListAdapter
import com.example.myapplication.utils.DataState
import com.example.myapplication.viewmodel.WeatherViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: WeatherViewmodel by viewModels()
    private val args : ListFragmentArgs by navArgs()
    private lateinit var binding: FragmentListBinding
    private val TAG = "ListFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
        val weatherListAdapter = WeatherListAdapter()
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
                    Log.e(TAG,dataState.data.toString())
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                    Log.e(TAG,dataState.exception.message.toString())
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

//    override fun onItemClicked(weather: Weather) {
//        val action = ListFragmentDirections.actionListFragment2ToDetailFragment2(weather)
//        findNavController().navigate(action)
//    }
}
