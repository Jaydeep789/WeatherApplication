package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        val data = args.weather
        binding.apply {
            val tempInDouble = 1.8 * (data.temperature - 273) + 32
            val feelsInDouble = 1.8 * (data.feels_like - 273) + 32

            detailTemp.text = tempInDouble.toInt().toString()
            detailsFeels.text = feelsInDouble.toInt().toString()
            detailType.text = data.type.map {
                it.weather_type
            }.toString().replace("[","").replace("]","")
            detailDescription.text = data.description.map {
                it.description
            }.toString().replace("[","").replace("]","")
        }
    }
}
