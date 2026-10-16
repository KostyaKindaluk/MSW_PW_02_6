package com.example.pw_02_6.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pw_02_6.data.checkEmail
import com.example.pw_02_6.data.checkPassword
import com.example.pw_02_6.data.checkStationName
import com.example.pw_02_6.data.isFormValid
import com.example.pw_02_6.components.FormHeader
import com.example.pw_02_6.components.InputField
import com.example.pw_02_6.components.LoadingIndicator
import com.example.pw_02_6.components.SubmitButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun FormScreen(modifier: Modifier = Modifier) {
    var stationName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var showErrors by rememberSaveable { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var isSuccess by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val nameError = if (showErrors || stationName.isNotEmpty()) checkStationName(stationName) else null
    val emailError = if (showErrors || email.isNotEmpty()) checkEmail(email) else null
    val passwordError = if (showErrors || password.isNotEmpty()) checkPassword(password) else null

    val onSubmitClick = {
        showErrors = true
        isSuccess = false
        if (isFormValid(stationName, email, password)) {
            scope.launch {
                isLoading = true
                delay(2000.milliseconds)
                isLoading = false
                isSuccess = true
            }
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isLargeScreen = maxWidth > 600.dp

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .heightIn(min = maxHeight)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = if (isLargeScreen) 800.dp else 500.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FormHeader("Реєстрація біогазової установки")

                if (isLargeScreen) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        InputField(
                            value = stationName,
                            onValueChange = { stationName = it },
                            label = "Назва установки",
                            hint = "Наприклад: Біогазова установка №1",
                            error = nameError,
                            modifier = Modifier.weight(1f)
                        )
                        InputField(
                            value = email,
                            onValueChange = { email = it },
                            label = "Email оператора",
                            hint = "Наприклад: operator@gmail.com",
                            error = emailError,
                            keyboardType = KeyboardType.Email,
                            modifier = Modifier.weight(1f)
                        )
                    }
                } else {
                    InputField(
                        value = stationName,
                        onValueChange = { stationName = it },
                        label = "Назва установки",
                        hint = "Наприклад: Біогазова установка №1",
                        error = nameError
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    InputField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email оператора",
                        hint = "Наприклад: operator@gmail.com",
                        error = emailError,
                        keyboardType = KeyboardType.Email
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                InputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Пароль",
                    hint = "Мінімум 8 символів, літера, цифра і спецсимвол",
                    error = passwordError,
                    isPassword = true,
                    keyboardType = KeyboardType.Password
                )

                Spacer(modifier = Modifier.height(8.dp))

                SubmitButton(
                    text = "Підтвердити",
                    enabled = !isLoading,
                    onClick = onSubmitClick
                )

                if (isLoading) {
                    LoadingIndicator()
                }

                if (isSuccess) {
                    Text(
                        text = "Дані успішно збережено",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}