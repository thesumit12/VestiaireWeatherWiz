package com.slakra.vestiaireweatherwiz.weeklyforecast

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.slakra.common.BaseActivity
import com.slakra.vestiaireweatherwiz.R
import com.slakra.vestiaireweatherwiz.databinding.ActivityWeeklyForecastBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeeklyForecastActivity : BaseActivity<ActivityWeeklyForecastBinding>(R.layout.activity_weekly_forecast) {

    private lateinit var weeklyForecastBinding: ActivityWeeklyForecastBinding
    private val viewModel: WeeklyForecastViewModel by viewModel()
    private lateinit var weeklyForecastAdapter: WeeklyForecastAdapter

    override fun initComponents(
        savedInstanceState: Bundle?,
        binding: ActivityWeeklyForecastBinding
    ) {
        weeklyForecastBinding = binding
        setUpRecyclerView()
        observeLiveEvents()
    }

    private fun setUpRecyclerView() {
        weeklyForecastAdapter = WeeklyForecastAdapter(this)
        weeklyForecastBinding.rvWeekly.apply {
            adapter = weeklyForecastAdapter
            layoutManager = LinearLayoutManager(this@WeeklyForecastActivity)
        }
    }

    private fun observeLiveEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.getDailyWeatherData().collect {
                weeklyForecastAdapter.submitList(it)
            }
        }
    }
}