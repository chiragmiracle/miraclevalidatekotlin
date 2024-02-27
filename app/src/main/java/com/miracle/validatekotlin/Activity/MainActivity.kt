package com.miracle.validatekotlin.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miracle.validatekotlin.ui.theme.textFont
import com.miracle.validationutility.Validation
import com.miracle.validationutility.Validation.generatePassword

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private var phoneNumber = mutableStateOf("")
    private var numberResult = mutableStateOf("")

    private var email = mutableStateOf("")
    private var emailResult = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeUI()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun HomeUI() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                phoneNumberValidation()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Gray)
                )
                emailValidation()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Gray)
                )
                rendomPassword()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Gray)
                )
            }
        }
    }

    @Composable
    fun rendomPassword() {
        val context = LocalContext.current
        val radioOptions = listOf("8", "10", "12")
        var selectedOption by remember { mutableStateOf(radioOptions[0]) }
        var includeCapital by remember { mutableStateOf(false) }
        var includeSmall by remember { mutableStateOf(false) }
        var includeNumber by remember { mutableStateOf(false) }
        var includeSpecial by remember { mutableStateOf(false) }
        var password by remember { mutableStateOf("") }

        val initialCheckboxStates = remember { mutableStateOf(listOf(false, false, false, false)) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Generate Random Password",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Checkbox(
                    checked = includeCapital,
                    onCheckedChange = { includeCapital = it },
                    modifier = Modifier.weight(1f),
                    enabled = !password.isNotEmpty()
                )
                Text(text = "Capital")

                Checkbox(
                    checked = includeSmall,
                    onCheckedChange = { includeSmall = it },
                    modifier = Modifier.weight(1f),
                    enabled = !password.isNotEmpty()
                )
                Text(text = "Small")

                Checkbox(
                    checked = includeNumber,
                    onCheckedChange = { includeNumber = it },
                    modifier = Modifier.weight(1f),
                    enabled = !password.isNotEmpty()
                )
                Text(text = "Number")

                Checkbox(
                    checked = includeSpecial,
                    onCheckedChange = { includeSpecial = it },
                    modifier = Modifier.weight(1f),
                    enabled = !password.isNotEmpty()
                )
                Text(text = "Special")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                radioOptions.forEach { text ->
                    RadioButton(
                        selected = selectedOption == text,
                        onClick = { selectedOption = text },
                        modifier = Modifier.weight(1f),
                        enabled = !password.isNotEmpty()
                    )
                    Text(text = text)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (!includeCapital && !includeSmall && !includeNumber && !includeSpecial) {
                        // Show a message or handle the scenario where no checkboxes are selected
                        return@Button
                    }

                    val passwordLength = selectedOption.toIntOrNull() ?: 8
                    password = generatePassword(passwordLength, includeCapital, includeSmall, includeNumber, includeSpecial)
                    initialCheckboxStates.value = listOf(includeCapital, includeSmall, includeNumber, includeSpecial)
                },
                enabled = !password.isNotEmpty()
            ) {
                Text(text = "Generate")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your password is: $password",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }


    @Composable
    private fun emailValidation() {
        Text(
            text = "Email Validation",
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = textFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)
        )
        OutlinedTextField(value = email.value,
            onValueChange = { email.value = it },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = textFont,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
            ),
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 8.dp, 15.dp, 15.dp),
            label = { Text("Enter Email") })
        Button(onClick = {
            emailResult.value = if (Validation.validateEmail(email.value)) {
                "Valid Email"
            } else {
                "Invalid Email"
            }
        }) {
            Text(
                text = "Email Validation",
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = textFont,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
        }
        Text(
            text = emailResult.value,
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = textFont,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)
        )
    }

    @Composable
    private fun phoneNumberValidation() {
        Text(
            text = "Phone Number Validation",
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = textFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)
        )
        OutlinedTextField(value = phoneNumber.value,
            onValueChange = { if (it.length <= 13) phoneNumber.value = it },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = textFont,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Phone,
            ),
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 8.dp, 15.dp, 15.dp),
            label = { Text("Enter Phone Number") })
        Button(onClick = {
            numberResult.value = if (Validation.validatePhoneNumber(phoneNumber.value)) {
                "Valid Phone Number"
            } else {
                "Invalid Phone Number"
            }
        }) {
            Text(
                text = "Phone Number Validation",
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = textFont,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
        }
        Text(
            text = numberResult.value,
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = textFont,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)
        )
    }

}