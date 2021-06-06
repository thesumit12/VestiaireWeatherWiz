package com.slakra.vestiaireweatherwiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.slakra.common.BaseActivity
import com.slakra.common.ProgressState
import com.slakra.common.utils.DateTimeUtil
import com.slakra.common.ResultState
import com.slakra.domain.entity.CurrentWeatherEntity
import com.slakra.vestiaireweatherwiz.databinding.ActivityMainBinding
import com.slakra.vestiaireweatherwiz.weeklyforecast.WeeklyForecastActivity
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter

    override fun initComponents(savedInstanceState: Bundle?, binding: ActivityMainBinding) {
        mainBinding = binding
        if (DateTimeUtil.isDay()) {
            mainBinding.parentLayout.setBackgroundResource(R.drawable.day_sky)
        }else {
            mainBinding.parentLayout.setBackgroundResource(R.drawable.night_sky)
        }
        viewModel.refreshWeatherData()
        setUpRecyclerView()
        observeLiveEvents()
        setErrorClickListeners()

        mainBinding.contentMain.tvSeeAll.setOnClickListener {
            val intent = Intent(this, WeeklyForecastActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeLiveEvents() {
        viewModel.state.observe(this, {
            mainBinding.state = it
        })
        viewModel.errorMsg.observe(this, {
            mainBinding.errorMsg = it
        })
        lifecycleScope.launchWhenStarted {
            viewModel.currentWeatherFlow.collect {  result->
                when(result) {
                    is ResultState.Success<CurrentWeatherEntity> -> {
                        mainBinding.currentWeather = result.data
                        mainBinding.contentError.btnCache.visibility = View.VISIBLE
                    }
                    else -> {
                        mainBinding.state = ProgressState.ERROR
                    }
                }

            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getHourlyWeatherData().collect {
                hourlyWeatherAdapter.submitList(it)
            }
        }
    }

    private fun setUpRecyclerView() {
        hourlyWeatherAdapter = HourlyWeatherAdapter(this)
        mainBinding.contentMain.rvHourlyWeather.apply {
            adapter = hourlyWeatherAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setErrorClickListeners() {
        mainBinding.contentError.btnTryAgain.setOnClickListener {
            viewModel.refreshWeatherData()
        }

        mainBinding.contentError.btnCache.setOnClickListener {
            mainBinding.state = ProgressState.SUCCESS
        }
    }
}