package com.miracle.validatekotlin.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern

class EmailValidation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    companion object {
        fun isValidEmail(email: String): Boolean {
            val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
            val pattern = Pattern.compile(emailRegex)
            return pattern.matcher(email).matches()
        }
    }

    @Composable
    fun MyApp() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            EmailValidationScreen()
        }
    }

}


@Composable
fun EmailValidationScreen() {
    var emailText by remember { mutableStateOf(TextFieldValue()) }
    var isValidEmail by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = emailText,
            onValueChange = {
                emailText = it
                isValidEmail = EmailValidation.isValidEmail(it.text)
            },
            singleLine = true,
            placeholder = { Text(text = "Enter your email") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { focusRequester.requestFocus() },
            enabled = !isValidEmail
        ) {
            Text(text = "Check Validity")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (emailText.text.isNotEmpty()) {
            if (isValidEmail) {
                Text(text = "Valid email address", style = MaterialTheme.typography.body1)
            } else {
                Text(text = "Invalid email address", style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
fun EmailValidationApp() {
    MaterialTheme {
        EmailValidationScreen()
    }
}