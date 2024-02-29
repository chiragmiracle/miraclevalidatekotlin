package com.miracle.validatekotlin.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
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
import com.miracle.validationutility.Validation
import com.miracle.validationutility.Validation.LengthConvert
import com.miracle.validationutility.Validation.MassConvert
import com.miracle.validationutility.Validation.compareDateTime
import com.miracle.validationutility.Validation.compareDateTimeAgo
import com.miracle.validationutility.Validation.generatePassword
import com.miracle.validationutility.Validation.volumeConvert
import com.miracle.validationutility.WordToNumberConverter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private var phoneNumber = mutableStateOf("")
    private var numberResult = mutableStateOf("")

    private var email = mutableStateOf("")
    private var emailResult = mutableStateOf("")

    private lateinit var dateFormat: SimpleDateFormat
    private lateinit var timeFormat: SimpleDateFormat
    private lateinit var calendar: Calendar
    private lateinit var currentDate: Date

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
                WordToNumberConverterScreen()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Gray)
                )
                SelectDateTime()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Gray)
                )
                LengthConverter()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Gray)
                )
                MassConverter()
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
                rendomPassword()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Gray)
                )
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
            }
        }
    }

    @Composable
    fun WordToNumberConverterScreen() {
        var inputWords by remember { mutableStateOf(TextFieldValue()) }
        var result by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(10.dp, 0.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Word To Number Converter",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = textHeading,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = inputWords,
                onValueChange = { inputWords = it },
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
                    .fillMaxWidth(),
                label = {
                    Text(
                        "Enter Word",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = textReg,
                        fontWeight = FontWeight.Normal,
                    )
                }
            )
            Button(
                onClick = {
                    val resultValue = WordToNumberConverter.convertWordToNumber(inputWords.text)
                    result = if (resultValue == -1L) "Invalid input" else resultValue.toString()
                },
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Convert",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = textBold,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = "Result : " + result,
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = textReg,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    @Composable
    fun SelectDateTime() {
        val context = LocalContext.current
        dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        calendar = Calendar.getInstance()
        currentDate = calendar.time
        var selectedDateTime by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "How many times and date ago",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = textHeading,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = {
                    showDateTimePicker(context) { selectedDate ->
                        selectedDateTime = selectedDate
                    }
                },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    "Select Date Time",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = textBold,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = "Result : " + selectedDateTime,
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = textReg,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    private fun showDateTimePicker(context: android.content.Context, callback: (String) -> Unit) {
        val currentDateCalendar = Calendar.getInstance()
        val year = currentDateCalendar.get(Calendar.YEAR)
        val month = currentDateCalendar.get(Calendar.MONTH)
        val dayOfMonth = currentDateCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            context,
            { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        val selectedDate = calendar.time
                        val selectedDateTime =
                            dateFormat.format(selectedDate) + " " + timeFormat.format(
                                selectedDate
                            )
                        if (selectedDate.after(currentDate)) {
                            Toast.makeText(
                                context,
                                "Please select a date and time before the current date and time",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val selectedDateTimeCalendar = Calendar.getInstance()
                            selectedDateTimeCalendar.time = selectedDate
                            val difference =
                                compareDateTime(selectedDateTimeCalendar, Calendar.getInstance())
                            val differenceAgo =
                                compareDateTimeAgo(selectedDateTimeCalendar, Calendar.getInstance())
                            callback.invoke("\n$selectedDateTime\n$difference\n$differenceAgo")
                        }
                    },
                    hour,
                    minute,
                    false
                ).show()
            },
            year,
            month,
            dayOfMonth
        ).apply {
            datePicker.maxDate = currentDateCalendar.timeInMillis
        }.show()
    }

    @Composable
    fun LengthConverter() {
        var massValue by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }
        var fromUnit by remember { mutableStateOf(0) }
        var toUnit by remember { mutableStateOf(0) }

        val units = listOf(
            "Kilometer",
            "Meter",
            "Centimeter",
            "Millimeter",
            "Micrometer",
            "Nanometer",
            "Mile",
            "Yard",
            "Foot",
            "Inch",
            "Nautical Mile"
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Length Converter",
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
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    Arrangement.SpaceBetween
                ) {
                    CustomeSpinner(
                        items = units,
                        selected = fromUnit,
                        onSelected = { fromUnit = it }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(value = massValue,
                        onValueChange = { massValue = it },
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
                                "Enter Length",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = textReg,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "=",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontFamily = textHeading,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .fillMaxWidth()
                        .weight(1f)
                        .fillMaxHeight(),
                ) {
                    CustomeSpinner(
                        items = units,
                        selected = toUnit,
                        onSelected = { toUnit = it }
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Box(
                        Modifier
                            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(55.dp),
                        Alignment.Center
                    ) {
                        Text(
                            text = if (result.isNotEmpty()) result else "Result",
                            style = MaterialTheme.typography.body1,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .background(Color.White),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    val value = massValue.toDoubleOrNull() ?: return@Button
                    val unitFrom = units[fromUnit]
                    val unitTo = units[toUnit]
                    result = LengthConvert(value, unitFrom, unitTo).toString()
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
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    @Composable
    fun MassConverter() {
        var massValue by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }
        var fromUnit by remember { mutableStateOf(0) }
        var toUnit by remember { mutableStateOf(0) }

        val units = listOf(
            "Tonne",
            "Kilogram",
            "Gram",
            "Milligram",
            "Microgram",
            "Imperial Ton",
            "US Ton",
            "Stone",
            "Pound",
            "Ounce"
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mass Converter",
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
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    Arrangement.SpaceBetween
                ) {
                    CustomeSpinner(
                        items = units,
                        selected = fromUnit,
                        onSelected = { fromUnit = it }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(value = massValue,
                        onValueChange = { massValue = it },
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
                                "Enter Mass",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = textReg,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "=",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontFamily = textHeading,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .fillMaxWidth()
                        .weight(1f)
                        .fillMaxHeight(),
                ) {
                    CustomeSpinner(
                        items = units,
                        selected = toUnit,
                        onSelected = { toUnit = it }
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Box(
                        Modifier
                            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(55.dp),
                        Alignment.Center
                    ) {
                        Text(
                            text = if (result.isNotEmpty()) result else "Result",
//                            text = result,
                            style = MaterialTheme.typography.body1,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .background(Color.White),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    val value = massValue.toDoubleOrNull() ?: return@Button
                    val unitFrom = units[fromUnit]
                    val unitTo = units[toUnit]
                    result = MassConvert(value.toString(), unitFrom, unitTo).toString()
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
            Spacer(modifier = Modifier.height(10.dp))
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
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    Arrangement.SpaceBetween
                ) {
                    CustomeSpinner(
                        items = units,
                        selected = selectedUnit1,
                        onSelected = { selectedUnit1 = it }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
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
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "=",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontFamily = textHeading,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .fillMaxWidth()
                        .weight(1f)
                        .fillMaxHeight(),
                ) {
                    CustomeSpinner(
                        items = units,
                        selected = selectedUnit2,
                        onSelected = { selectedUnit2 = it }
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Box(
                        Modifier
                            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(55.dp),
                        Alignment.Center
                    ) {
                        Text(
                            text = if (result.isNotEmpty()) result else "Result",
                            style = MaterialTheme.typography.body1,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontFamily = textReg,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .background(Color.White),
                            textAlign = TextAlign.Center
                        )
                    }
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
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    @Composable
    fun CustomeSpinner(items: List<String>, selected: Int, onSelected: (Int) -> Unit) {
        var expanded by remember { mutableStateOf(false) }
        Box(
            Modifier
                .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .fillMaxWidth()
                .height(55.dp),
            Alignment.Center
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