package com.slakra.vestiaireweatherwiz.config

/**
 * This class can be used for remote configuration values
 * Like we can update temp -- variance value either by config api or by Firebase Remote configs etc
 * @author sumitlakra
 * @date 06/06/2021
 */
object RemoteConfig {

    private var VARIANCE_MIN_TEMP = 9
    private var VARIANCE_MAX_TEMP = 26

    fun getMinTemperature() = VARIANCE_MIN_TEMP

    fun getMaxTemperature() = VARIANCE_MAX_TEMP

    fun setMinTemperature(temp: Int) {
        VARIANCE_MIN_TEMP = temp
    }

    fun setMaxTemperature(temp: Int) {
        VARIANCE_MAX_TEMP = temp
    }
}