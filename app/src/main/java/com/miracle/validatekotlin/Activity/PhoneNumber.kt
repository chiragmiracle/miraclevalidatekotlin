@file:OptIn(ExperimentalMaterial3Api::class)

package com.miracle.validatekotlin.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class PhoneNumber : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneNumberValidatorApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PhoneNumberValidatorApp() {
    var phoneNumber by remember { mutableStateOf("") }
    var validationResult by remember { mutableStateOf<String?>(null) }
    var isEmptyOrInvalid by remember { mutableStateOf(false) }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { input ->
                        phoneNumber = input
                        validationResult = null // Reset validation result
                        isEmptyOrInvalid = false // Reset error state when user starts typing
                    },
                    label = { Text("Enter phone number") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    isError = isEmptyOrInvalid,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (phoneNumber.isBlank()) {
                        isEmptyOrInvalid = true
                        validationResult = null
                    } else if (validatePhoneNumber(phoneNumber)) {
                        isEmptyOrInvalid = false
                        validationResult = "Valid phone number"
                    } else {
                        isEmptyOrInvalid = true
                        validationResult = "Invalid phone number"
                    }
                }) {
                    Text("Validate")
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (validationResult != null) {
                    Text(
                        text = validationResult!!,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

fun validatePhoneNumber(phoneNumber: String): Boolean {
    return phoneNumber.length in 7..13 && phoneNumber.all { it.isDigit() || it == '+' }
}