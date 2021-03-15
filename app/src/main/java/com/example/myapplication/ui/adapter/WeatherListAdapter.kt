package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.model.Weather

class WeatherListAdapter(private val listener: OnClickListener) :
    ListAdapter<Weather, WeatherListAdapter.WeatherViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    interface OnClickListener {
        fun onItemClicked(weather: Weather)
    }

    inner class WeatherViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    val weather = getItem(position)
                    listener.onItemClicked(weather)
                }
            }
        }

        fun bind(weather: Weather) {
            binding.apply {
                val tempInDouble = 1.8 * (weather.temperature - 273) + 32
                val fahrenheit = itemView.context.resources.getString(R.string.fahrenheit)
                val calculatedTemperature = String.format(fahrenheit, tempInDouble.toInt())
                temperature.text = calculatedTemperature
                type.text = weather.type.map {
                    it.weather_type
                }.toString().replace("[", "").replace("]", "")
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.temperature == newItem.temperature
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }
    }
}
