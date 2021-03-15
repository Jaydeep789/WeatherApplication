package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)

        (activity as AppCompatActivity).supportActionBar?.title = args.city
        val data = args.weather
        binding.apply {
            val tempInDouble = 1.8 * (data.temperature - 273) + 32
            val fahrenheit = requireContext().resources.getString(R.string.degree)
            val calculatedTemperature = String.format(fahrenheit, tempInDouble.toInt())

            val feelsInDouble = 1.8 * (data.feels_like - 273) + 32
            val feelsLike = requireContext().resources.getString(R.string.feels_like)
            val feels = String.format(feelsLike, feelsInDouble.toInt())

            detailTemp.text = calculatedTemperature
            detailsFeels.text = feels
            detailType.text = data.type.map {
                it.weather_type
            }.toString().replace("[", "").replace("]", "")
            detailDescription.text = data.description.map {
                it.description
            }.toString().replace("[", "").replace("]", "")
        }
    }
}
