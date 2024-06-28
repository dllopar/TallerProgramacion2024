package com.example.tp_pokemon_2024.android.util

import java.util.Locale

fun String.changeFirstLetterToUppercaseAndDeleteMiddleDash(): String {
    return this.changeFirstLetterToUppercase().replace("-", " ")
}

private fun String.changeFirstLetterToUppercase(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}
