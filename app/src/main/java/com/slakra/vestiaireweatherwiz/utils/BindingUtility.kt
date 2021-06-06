package com.slakra.vestiaireweatherwiz.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.slakra.common.utils.DateTimeUtil
import com.slakra.vestiaireweatherwiz.R
import com.slakra.vestiaireweatherwiz.config.RemoteConfig

@BindingAdapter(
    value = [
        "roundedCorner",
        "topLeftRadius",
        "bottomLeftRadius",
        "topRightRadius",
        "bottomRightRadius",
        "fillColor",
        "strokeColor"],
    requireAll = false)
fun setRoundedCorner(view: View, roundedCorner: Boolean, topLeft: Float?,
                     bottomLeft: Float?, topRight: Float?, bottomRight: Float?,
                     @ColorRes fillColor: Int?, @ColorRes strokeColor: Int?) {
    if (roundedCorner) {
        val radius = view.context.resources.getDimension(R.dimen.margin_24)
        val modal = ShapeAppearanceModel().toBuilder()
        val strColor = strokeColor ?: R.color.white
        if (topLeft == null) {
            modal.setTopLeftCorner(CornerFamily.ROUNDED, radius)
        } else {
            modal.setTopLeftCorner(CornerFamily.ROUNDED, topLeft)
        }

        if (bottomLeft == null) {
            modal.setBottomLeftCorner(CornerFamily.ROUNDED, radius)
        } else {
            modal.setBottomLeftCorner(CornerFamily.ROUNDED, bottomLeft)
        }

        if (topRight == null) {
            modal.setTopRightCorner(CornerFamily.ROUNDED, radius)
        } else {
            modal.setTopRightCorner(CornerFamily.ROUNDED, topRight)
        }

        if (bottomRight == null) {
            modal.setBottomRightCorner(CornerFamily.ROUNDED, radius)
        } else {
            modal.setBottomRightCorner(CornerFamily.ROUNDED, bottomRight)
        }

        val shapeDrawable = MaterialShapeDrawable(modal.build())
        if (fillColor == null) {
            shapeDrawable.fillColor = ContextCompat.getColorStateList(view.context, R.color.white)
        } else {
            shapeDrawable.fillColor = ContextCompat.getColorStateList(view.context, fillColor)
        }

        shapeDrawable.setStroke(2F, ContextCompat.getColor(view.context, strColor))

        ViewCompat.setBackground(view, shapeDrawable)
    }
}

@BindingAdapter(value = ["iconCode", "timeStamp"], requireAll = false)
fun setWeatherIcon(imageView: ImageView, code: Int, time: Long?) {
    val isDay = DateTimeUtil.isDay(time)
    val weatherIcon = when(code) {
        in 200..232 -> R.drawable.ic_thunder_storm
        in 300..531 -> {
            if (isDay) {
                R.drawable.ic_rain
            }else {
                R.drawable.ic_rain_n
            }
        }
        in 700..781 -> R.drawable.ic_scattered_clouds
        801 -> if (isDay) {
            R.drawable.ic_few_clouds_d
        }else {
            R.drawable.ic_few_cloud_n
        }
        in 802..804 -> if (isDay) {
            R.drawable.ic_cloud_d
        }else {
            R.drawable.ic_scattered_clouds
        }
        else -> {
            if (isDay) {
                R.drawable.ic_sunny
            }else {
                R.drawable.ic_clear_n
            }
        }
    }
    imageView.setImageResource(weatherIcon)
}

@BindingAdapter(value = ["description", "temp"])
fun setWeatherDescription(textview: TextView, description: String?, temp: String?) {
    textview.text = when(temp?.toInt()) {
        in RemoteConfig.getMaxTemperature()..Int.MAX_VALUE -> textview.context.getString(R.string.hot)
        in Int.MIN_VALUE..RemoteConfig.getMinTemperature() -> textview.context.getString(R.string.cold)
        else -> description
    }
}
