package com.slakra.vestiaireweatherwiz

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slakra.common.utils.DateTimeUtil
import com.slakra.domain.entity.HourlyWeatherEntity
import com.slakra.vestiaireweatherwiz.databinding.HourlyWeatherItemBinding

/**
 * Recyclerview Adapter to show Hourly weather data
 * @author sumitlakra
 * @date 06/05/2021
 */
class HourlyWeatherAdapter(private val context: Context) :
    ListAdapter<HourlyWeatherEntity, HourlyWeatherAdapter.HourlyWeatherItemViewHolder>(
        diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherItemViewHolder {
        val binding = DataBindingUtil.inflate<HourlyWeatherItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.hourly_weather_item,
            parent,
            false
        )
        return HourlyWeatherItemViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: HourlyWeatherItemViewHolder, position: Int) {
        holder.bind(getItem(position), holder.adapterPosition)
    }

    class HourlyWeatherItemViewHolder(
        private val binding: HourlyWeatherItemBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: HourlyWeatherEntity, position: Int) {
            binding.hourlyWeatherEntity = entity
            if (position != 0) {
                binding.fillColor = if (DateTimeUtil.isDay()) {
                    R.color.day_fill_color
                }else {
                    R.color.night_fill_color
                }
                binding.tvTime.text = DateTimeUtil.formatTime(entity.timestamp, DateTimeUtil.H_FORMAT)
                binding.tvTemperature.setTextColor(ContextCompat.getColor(context, R.color.text_white))
            }else {
                binding.tvTime.text = context.getString(R.string.now)
                binding.fillColor = R.color.white
                binding.tvTemperature.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }
    }

    companion object {

        private val diffUtil = object : DiffUtil.ItemCallback<HourlyWeatherEntity>() {
            override fun areItemsTheSame(
                oldItem: HourlyWeatherEntity,
                newItem: HourlyWeatherEntity
            ): Boolean = oldItem.timestamp == newItem.timestamp

            override fun areContentsTheSame(
                oldItem: HourlyWeatherEntity,
                newItem: HourlyWeatherEntity
            ): Boolean = oldItem == newItem
        }
    }
}