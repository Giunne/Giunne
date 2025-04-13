package com.project.giunne.common.util

fun String.isValidPassword(): Boolean {
    val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#\$%^&*()_+\\-=]{8,}$")
    return regex.matches(this)
}

fun String.isNumeric(): Boolean {
    return this.toIntOrNull() != null || this.toDoubleOrNull() != null
}

fun String.removeSpaceUrl(): String {
    return this.replace(" ", "%20")
}