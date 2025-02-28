package com.project.giunne.common.util

fun String.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$"
    return this.matches(Regex(passwordPattern))
}