package com.kiwi.dailyoffer.utils

import android.graphics.Color
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun parseDate(date: String?): Date? {
            if (date != null) {
                val f2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

                try {
                    return f2.parse(date)
                } catch (e: Exception) {
                    Log.d("parseDate", "$e.cause $e.message")
                    return null
                }
            } else {
                return null
            }
        }

        fun toKiwiDateFormat(date : Date) : String {
            val df = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())

            return df.format(date)
        }

        fun getColorWithAlpha(color: Int, ratio: Float): Int {
            var newColor = 0
            val alpha = Math.round(Color.alpha(color) * ratio)
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)
            newColor = Color.argb(alpha, r, g, b)
            return newColor
        }
    }
}