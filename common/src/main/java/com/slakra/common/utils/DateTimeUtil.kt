package com.slakra.common.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Util class for formatting date time
 * @author sumitlakra
 * @date 06/05/2021
 */
object DateTimeUtil {

    const val H_MM_FORMAT = "h:mm a"
    const val H_FORMAT = "h a"
    const val DATE_FORMAT = "EEE, d MMM"
    const val DAY_DATE_FORMAT = "EEEE, d MMM"

    /**
     * Utility function to format time
     * @param milliSeconds [Long]
     * @param format [String]
     * @return [String]
     * @author sumitlakra
     * @date 06/05/2021
     */
    @JvmStatic
    fun formatTime(milliSeconds: Long, format: String): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(Date(milliSeconds*1000L))
    }

    /**
     * Utility function to determine day/night
     * @param milliSeconds [Long]
     * @return [Boolean]
     * @author sumitlakra
     * @date 06/05/2021
     */
    fun isDay(milliSeconds: Long?= null): Boolean {
        val calendar = Calendar.getInstance()
        if (milliSeconds != null) {
            calendar.timeInMillis = milliSeconds * 1000
        }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return hour in 6..18
    }
}