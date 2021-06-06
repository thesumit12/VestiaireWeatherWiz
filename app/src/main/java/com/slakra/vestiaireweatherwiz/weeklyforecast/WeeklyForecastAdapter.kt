package com.slakra.vestiaireweatherwiz.weeklyforecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slakra.domain.entity.DailyWeatherEntity
import com.slakra.vestiaireweatherwiz.R
import com.slakra.vestiaireweatherwiz.databinding.DailyWeatherItemBinding

/**
 * Recyclerview adapter to show Weekly forecast data
 * @author sumitlakra
 * @date 06/05/2021
 */
class WeeklyForecastAdapter(private val context: Context) :
    ListAdapter<DailyWeatherEntity, WeeklyForecastAdapter.DailyForecastItemViewHolder>(
        diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastItemViewHolder {
        val binding: DailyWeatherItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.daily_weather_item,
            parent,
            false
        )
        return DailyForecastItemViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: DailyForecastItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DailyForecastItemViewHolder(private val binding: DailyWeatherItemBinding,
    private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: DailyWeatherEntity) {
            binding.dailyItem = entity
            binding.parentLayout.setOnClickListener {
                if (binding.groupDetail.isVisible) {
                    binding.groupDetail.visibility = View.GONE
                    binding.parentLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                }else {
                    binding.groupDetail.visibility = View.VISIBLE
                    binding.parentLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.text_grey))
                }
            }
        }
    }

    companion object {

        private val diffUtil = object : DiffUtil.ItemCallback<DailyWeatherEntity>() {

            override fun areItemsTheSame(
                oldItem: DailyWeatherEntity,
                newItem: DailyWeatherEntity
            ): Boolean = oldItem.timeStamp == newItem.timeStamp

            override fun areContentsTheSame(
                oldItem: DailyWeatherEntity,
                newItem: DailyWeatherEntity
            ): Boolean = oldItem == newItem
        }
    }
}