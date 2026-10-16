package com.example.pw_02_6.data

import android.util.Patterns

const val MIN_PASSWORD_LENGTH = 8

fun checkStationName(name: String): String? {
    if (name.isBlank()) {
        return "Введіть назву установки"
    }
    return null
}

fun checkEmail(email: String): String? {
    if (email.isBlank()) {
        return "Введіть email"
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
        return "Неправильний формат email"
    }
    return null
}

fun checkPassword(password: String): String? {
    if (password.isEmpty()) {
        return "Введіть пароль"
    }
    if (password.length < MIN_PASSWORD_LENGTH) {
        return "Пароль має містити мінімум $MIN_PASSWORD_LENGTH символів"
    }
    if (!password.any { it.isLetter() }) {
        return "Пароль має містити хоча б одну літеру"
    }
    if (!password.any { it.isDigit() }) {
        return "Пароль має містити хоча б одну цифру"
    }
    if (!password.any { !it.isLetterOrDigit() && !it.isWhitespace() }) {
        return "Пароль має містити хоча б один спецсимвол (!@#$%...)"
    }
    return null
}

fun isFormValid(name: String, email: String, password: String): Boolean {
    return checkStationName(name) == null &&
        checkEmail(email) == null &&
        checkPassword(password) == null
}