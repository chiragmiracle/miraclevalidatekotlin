package com.miracle.validatekotlin.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miracle.validatekotlin.ui.theme.textBold
import com.miracle.validatekotlin.ui.theme.textHeading
import com.miracle.validatekotlin.ui.theme.textReg
import com.miracle.validationutility.Validation
import com.miracle.validationutility.Validation.generatePassword
import com.miracle.validationutility.Validation.volumeConvert

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
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
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
                VolumeConverterScreen()
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
    fun VolumeConverterScreen() {
        var selectedUnit1 by remember { mutableStateOf(0) }
        var selectedUnit2 by remember { mutableStateOf(0) }

        var volumeValue by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }

        val units = listOf(
            "US liquid gallon",
            "US liquid quart",
            "US liquid pint",
            "US legal cup",
            "US fluid ounce",
            "US tablespoon",
            "US teaspoon",
            "cubic meter",
            "liter",
            "milliliter",
            "imperial gallon",
            "imperial quart",
            "imperial pint",
            "imperial cup",
            "imperial fluid ounce",
            "imperial tablespoon",
            "imperial teaspoon",
            "cubic foot",
            "cubic inch"
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Volume Converter",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = textHeading,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 15.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.width(160.dp),
                    Arrangement.SpaceBetween
                ) {
                    VolumeSpinner(
                        items = units,
                        selected = selectedUnit1,
                        onSelected = { selectedUnit1 = it }
                    )
                    OutlinedTextField(value = volumeValue,
                        onValueChange = { volumeValue = it },
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = true,
                            keyboardType = KeyboardType.Number,
                        ),
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            Text(
                                "Enter Volume",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = textReg,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    )
//                    TextField(
//                        value = volumeValue,
//                        onValueChange = { volumeValue = it },
//                        label = { Text("Enter Volume") },
//                        singleLine = true,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                    )
                }
                Text(
                    text = "=",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontFamily = textHeading,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .height(100.dp)
                        .padding(horizontal = 8.dp),
                )
                Column(
                    modifier = Modifier
                        .width(160.dp)
                        .fillMaxHeight(),
                ) {
                    VolumeSpinner(
                        items = units,
                        selected = selectedUnit2,
                        onSelected = { selectedUnit2 = it }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = result,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = textReg,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    val value = volumeValue.toDoubleOrNull() ?: return@Button
                    val unitFrom = units[selectedUnit1]
                    val unitTo = units[selectedUnit2]
                    result = volumeConvert(value, unitFrom, unitTo).toString()
                }
            ) {
                Text(
                    text = "Convert",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = textBold,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

    @Composable
    fun VolumeSpinner(items: List<String>, selected: Int, onSelected: (Int) -> Unit) {
        var expanded by remember { mutableStateOf(false) }
        Box (
            modifier = Modifier
                .border(2.dp, Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = items[selected],
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = textReg,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEachIndexed { index, text ->
                    DropdownMenuItem(onClick = {
                        onSelected(index)
                        expanded = false
                    }) {
                        Text(
                            text,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
        }
    }


    @Composable
    fun rendomPassword() {
        val radioOptions = listOf("8", "10", "12")
        var selectedOption by remember { mutableStateOf(radioOptions[0]) }
        var includeCapital by remember { mutableStateOf(false) }
        var includeSmall by remember { mutableStateOf(false) }
        var includeNumber by remember { mutableStateOf(false) }
        var includeSpecial by remember { mutableStateOf(false) }
        var password by remember { mutableStateOf("") }
        var passwordGenerated by remember { mutableStateOf(false) }

        val initialCheckboxStates = remember { mutableStateOf(listOf(false, false, false, false)) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Generate Random Password",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = textHeading,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 15.dp)
            )

            Row(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 20.dp, 0.dp)
                    .fillMaxWidth()
                    .height(15.dp),
                Arrangement.Start
            ) {
                Checkbox(
                    checked = includeCapital,
                    onCheckedChange = {
                        includeCapital = it
                        if (passwordGenerated) passwordGenerated = false
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !passwordGenerated
                )
                Text(
                    text = "Capital",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = textReg,
                    fontWeight = FontWeight.Normal,
                )

                Checkbox(
                    checked = includeSmall,
                    onCheckedChange = {
                        includeSmall = it
                        if (passwordGenerated) passwordGenerated = false
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !passwordGenerated
                )
                Text(
                    text = "Small",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = textReg,
                    fontWeight = FontWeight.Normal,
                )

                Checkbox(
                    checked = includeNumber,
                    onCheckedChange = {
                        includeNumber = it
                        if (passwordGenerated) passwordGenerated = false
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !passwordGenerated
                )
                Text(
                    text = "Number",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = textReg,
                    fontWeight = FontWeight.Normal,
                )

                Checkbox(
                    checked = includeSpecial,
                    onCheckedChange = {
                        includeSpecial = it
                        if (passwordGenerated) passwordGenerated = false
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !passwordGenerated
                )
                Text(
                    text = "Special",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = textReg,
                    fontWeight = FontWeight.Normal,
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier
                    .width(200.dp)
                    .height(15.dp),
                Arrangement.SpaceBetween
            ) {
                radioOptions.forEach { text ->
                    RadioButton(
                        selected = selectedOption == text,
                        onClick = {
                            selectedOption = text
                            if (passwordGenerated) passwordGenerated = false
                        },
                        modifier = Modifier.weight(1f),
                        enabled = !passwordGenerated
                    )
                    Text(
                        text = text,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = textReg,
                        fontWeight = FontWeight.Normal,
                    )
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
                    password = generatePassword(
                        passwordLength,
                        includeCapital,
                        includeSmall,
                        includeNumber,
                        includeSpecial
                    )
                    initialCheckboxStates.value =
                        listOf(includeCapital, includeSmall, includeNumber, includeSpecial)
                    passwordGenerated = true
                },
                enabled = !passwordGenerated
            ) {
                Text(
                    text = "Generate",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = textBold,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = "Your password is: $password",
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = textReg,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }
    }

    @Composable
    private fun emailValidation() {
        Text(
            text = "Email Validation",
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = textHeading,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)
        )
        OutlinedTextField(value = email.value,
            onValueChange = { email.value = it },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = textReg,
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
            label = { Text("Enter Email") }
        )
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
                fontFamily = textBold,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
        }
        Text(
            text = "Result : " + emailResult.value,
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = textReg,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
    }

    @Composable
    private fun phoneNumberValidation() {
        Text(
            text = "Phone Number Validation",
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = textHeading,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)
        )
        OutlinedTextField(value = phoneNumber.value,
            onValueChange = { if (it.length <= 13) phoneNumber.value = it },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = textReg,
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
                fontFamily = textBold,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
        }
        Text(
            text = "Result : " + numberResult.value,
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = textReg,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
    }

}