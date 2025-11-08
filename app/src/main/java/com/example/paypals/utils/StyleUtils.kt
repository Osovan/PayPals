package com.example.paypals.utils

import androidx.compose.ui.graphics.Color

// Lista de colores predefinidos
val profileColors = listOf(
     Color(0xFFE57373),
     Color(0xFFF06292),
     Color(0xFFBA68C8),
     Color(0xFF9575CD),
     Color(0xFF7986CB),
     Color(0xFF64B5F6),
     Color(0xFF4DB6AC),
     Color(0xFF81C784),
     Color(0xFFDCE775),
     Color(0xFFFFB74D),
     Color(0xFFA1887F),
     Color(0xFF90A4AE)
)

fun getInitialAndColor(name: String): Pair<Char, Color> {
     val initial = name.trim().firstOrNull()?.uppercaseChar() ?: '?'
     val colorIndex = (name.hashCode() and 0xfffffff) % profileColors.size
     return Pair(initial, profileColors[colorIndex])
}