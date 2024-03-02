package com.miracle.validatekotlin.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miracle.validatekotlin.ui.theme.textBold
import com.miracle.validatekotlin.ui.theme.textHeading
import com.miracle.validatekotlin.ui.theme.textReg
import com.miracle.validationutility.FormValidation.MiracleFormValidation.validateForm

@OptIn(ExperimentalMaterial3Api::class)
class Form_Validation : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeUI()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun HomeUI() {
        var inputUserID by remember { mutableStateOf("") }
        var inputFirstName by remember { mutableStateOf("") }
        var inputLastName by remember { mutableStateOf("") }
        var inputPhone by remember { mutableStateOf("") }
        var inputEmail by remember { mutableStateOf("") }
        var inputPass1 by remember { mutableStateOf("") }
        var inputPass2 by remember { mutableStateOf("") }
        var inputZIP by remember { mutableStateOf("") }
        var validationErrors by remember { mutableStateOf(mutableMapOf<String, String>()) }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth()
                        .weight(9.1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Form Validation",
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontFamily = textHeading,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    OutlinedTextField(
                        value = inputUserID,
                        onValueChange = { inputUserID = it.take(15) },
                        textStyle = TextStyle.Default.copy(
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                        ),
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(
                                "Enter User ID",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = textReg,
                                fontWeight = FontWeight.Normal,
                            )
                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = if (validationErrors["user_id"].isNullOrEmpty()) Color.Black else Color.Red
//                        )

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1F)
                                .padding(end = 5.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            OutlinedTextField(
                                value = inputFirstName,
                                onValueChange = { inputFirstName = it.take(15) },
                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = textReg,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                ),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    keyboardType = KeyboardType.Text,
                                ),
                                singleLine = true,
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth(),
                                label = {
                                    Text(
                                        "Enter First Name",
                                        color = Color.Black,
                                        fontSize = 15.sp,
                                        fontFamily = textReg,
                                        fontWeight = FontWeight.Normal,
                                    )
                                },
//                                colors = TextFieldDefaults.outlinedTextFieldColors(
//                                    focusedBorderColor = if (validationErrors["first_name"].isNullOrEmpty()) Color.Black else Color.Red
//                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1F)
                                .padding(start = 5.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            OutlinedTextField(
                                value = inputLastName,
                                onValueChange = { inputLastName = it.take(15) },
                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = textReg,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                ),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    keyboardType = KeyboardType.Text,
                                ),
                                singleLine = true,
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth(),
                                label = {
                                    Text(
                                        "Enter Last Name",
                                        color = Color.Black,
                                        fontSize = 15.sp,
                                        fontFamily = textReg,
                                        fontWeight = FontWeight.Normal,
                                    )
                                },
//                                colors = TextFieldDefaults.outlinedTextFieldColors(
//                                    focusedBorderColor = if (validationErrors["last_name"].isNullOrEmpty()) Color.Black else Color.Red
//                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = inputPhone,
                        onValueChange = { inputPhone = it.take(13) },
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = true,
                            keyboardType = KeyboardType.Number,
                        ),
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(
                                "Enter Phone Number",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = textReg,
                                fontWeight = FontWeight.Normal,
                            )
                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = if (validationErrors["phone"].isNullOrEmpty()) Color.Black else Color.Red
//                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = inputEmail,
                        onValueChange = { inputEmail = it },
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                        ),
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(
                                "Enter Email ID",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = textReg,
                                fontWeight = FontWeight.Normal,
                            )
                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = if (validationErrors["email"].isNullOrEmpty()) Color.Black else Color.Red
//                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1F)
                                .padding(end = 5.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            OutlinedTextField(
                                value = inputPass1,
                                onValueChange = { inputPass1 = it.take(15) },
                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = textReg,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                ),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    keyboardType = KeyboardType.Password,
                                ),
                                singleLine = true,
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth(),
                                label = {
                                    Text(
                                        "Enter Password",
                                        color = Color.Black,
                                        fontSize = 15.sp,
                                        fontFamily = textReg,
                                        fontWeight = FontWeight.Normal,
                                    )
                                },
//                                colors = TextFieldDefaults.outlinedTextFieldColors(
//                                    focusedBorderColor = if (validationErrors["pass1"].isNullOrEmpty()) Color.Black else Color.Red
//                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1F)
                                .padding(start = 5.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            OutlinedTextField(
                                value = inputPass2,
                                onValueChange = { inputPass2 = it.take(15) },
                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = textReg,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                ),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    keyboardType = KeyboardType.Password,
                                ),
                                singleLine = true,
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth(),
                                label = {
                                    Text(
                                        "Re-enter password",
                                        color = Color.Black,
                                        fontSize = 15.sp,
                                        fontFamily = textReg,
                                        fontWeight = FontWeight.Normal,
                                    )
                                },
//                                colors = TextFieldDefaults.outlinedTextFieldColors(
//                                    focusedBorderColor = if (validationErrors["pass2"].isNullOrEmpty()) Color.Black else Color.Red
//                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = inputZIP,
                        onValueChange = { inputZIP = it.take(6) },
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = true,
                            keyboardType = KeyboardType.Number,
                        ),
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(
                                "Enter ZIP Code",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = textReg,
                                fontWeight = FontWeight.Normal,
                            )
                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = if (validationErrors["zip"].isNullOrEmpty()) Color.Black else Color.Red
//                        )
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))
                validationErrors.entries.forEach { (field, errorMessage) ->
                    if (errorMessage.isNotEmpty()) {
                        Text(
                            errorMessage,
                            color = Color.Red,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.9f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.padding(end = 20.dp),
                        onClick = {
                            inputUserID = ""
                            inputFirstName = ""
                            inputLastName = ""
                            inputPhone = ""
                            inputEmail = ""
                            inputPass1 = ""
                            inputPass2 = ""
                            inputZIP = ""
                        }) {
                        Text(
                            "Clear",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = textBold,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        modifier = Modifier.padding(start = 20.dp),
                        onClick = {
                            validationErrors = validateForm(
                                inputUserID,
                                inputFirstName,
                                inputLastName,
                                inputPhone,
                                inputEmail,
                                inputPass1,
                                inputPass2,
                                inputZIP
                            )

                            if (validationErrors.isEmpty()) {
                                inputUserID = TextFieldValue().toString()
                                inputFirstName = TextFieldValue().toString()
                                inputLastName = TextFieldValue().toString()
                                inputPhone = TextFieldValue().toString()
                                inputEmail = TextFieldValue().toString()
                                inputPass1 = TextFieldValue().toString()
                                inputPass2 = TextFieldValue().toString()
                                inputZIP = TextFieldValue().toString()
                            }
                        }) {
                        Text(
                            "Submit",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = textBold,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}