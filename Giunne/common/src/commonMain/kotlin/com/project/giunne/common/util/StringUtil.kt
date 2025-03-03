package com.project.giunne.common.util

fun String.isValidPassword(): Boolean {
    val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#\$%^&*()_+\\-=]{8,}$")
    return regex.matches(this)
}