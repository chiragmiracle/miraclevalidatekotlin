package com.miracle.validatekotlin.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.miracle.validatekotlin.ui.theme.textFont

class PhoneNumber : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        PhoneNumberValidationScreen()
    }
}

@Composable
fun PhoneNumberValidationScreen() {
    var selectedCountry by remember { mutableStateOf(Country("IN", "India")) }
    var phoneNumber by remember { mutableStateOf("") }
    var validationResult by remember { mutableStateOf<Boolean?>(null) }
    val context = LocalContext.current

    Column {
        Card(
            backgroundColor = Black, elevation = 6.dp, shape = RoundedCornerShape(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
            ) {
                Text(
                    text = "Phone Number Validation",
                    color = White,
                    fontSize = 20.sp,
                    fontFamily = textFont,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                CustomDropdown(
                    selectedItem = selectedCountry,
                    items = countryList,
                    onItemSelected = { selectedCountry = it }
                )
            }
            PhoneNumberInput(
                value = phoneNumber,
                onValueChange = { phoneNumber = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    validationResult =
                        validatePhoneNumber(context, selectedCountry.code, phoneNumber)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Validate")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (validationResult != null) {
                val resultText =
                    if (validationResult == true) "Valid phone number" else "Invalid phone number"
                Text(
                    text = resultText,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CustomDropdown(
    selectedItem: Country,
    items: List<Country>,
    onItemSelected: (Country) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(items.indexOf(selectedItem)) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Row {
            TextButton(
                onClick = { expanded = !expanded }
            ) {
                Text("Country: ${selectedItem.name} (${selectedItem.code})")
            }
            IconButton(onClick = { expanded = !expanded }
            ) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Down")
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, country ->
                DropdownMenuItem(onClick = {
                    onItemSelected(country)
                    selectedIndex = index
                    expanded = false
                }) {
                    Text("${country.name} (${country.code})")
                }
            }
        }
    }
}


@Composable
fun UpDownButtons(
    onUpClicked: () -> Unit,
    onDownClicked: () -> Unit
) {
    Column {
        IconButton(onClick = onUpClicked) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Up"
            )
        }
        IconButton(onClick = onDownClicked) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Down"
            )
        }
    }
}

@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it.take(10)) // Limit input to 10 characters
        },
        label = { Text("Phone Number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        modifier = Modifier.fillMaxWidth()
    )
}

data class Country(val code: String, val name: String)

private val countryList = listOf(
    Country("AF", "Afghanistan"),
    Country("AX", "Åland Islands"),
    Country("AL", "Albania"),
    Country("DZ", "Algeria"),
    Country("AS", "American Samoa"),
    Country("AD", "Andorra"),
    Country("AO", "Angola"),
    Country("AI", "Anguilla"),
    Country("AQ", "Antarctica"),
    Country("AG", "Antigua and Barbuda"),
    Country("AR", "Argentina"),
    Country("AM", "Armenia"),
    Country("AW", "Aruba"),
    Country("AU", "Australia"),
    Country("AT", "Austria"),
    Country("AZ", "Azerbaijan"),
    Country("BS", "Bahamas"),
    Country("BH", "Bahrain"),
    Country("BD", "Bangladesh"),
    Country("BB", "Barbados"),
    Country("BY", "Belarus"),
    Country("BE", "Belgium"),
    Country("BZ", "Belize"),
    Country("BJ", "Benin"),
    Country("BM", "Bermuda"),
    Country("BT", "Bhutan"),
    Country("BO", "Bolivia, Plurinational State of"),
    Country("BQ", "Bonaire, Sint Eustatius and Saba"),
    Country("BA", "Bosnia and Herzegovina"),
    Country("BW", "Botswana"),
    Country("BV", "Bouvet Island"),
    Country("BR", "Brazil"),
    Country("IO", "British Indian Ocean Territory"),
    Country("BN", "Brunei Darussalam"),
    Country("BG", "Bulgaria"),
    Country("BF", "Burkina Faso"),
    Country("BI", "Burundi"),
    Country("CV", "Cabo Verde"),
    Country("KH", "Cambodia"),
    Country("CM", "Cameroon"),
    Country("CA", "Canada"),
    Country("KY", "Cayman Islands"),
    Country("CF", "Central African Republic"),
    Country("TD", "Chad"),
    Country("CL", "Chile"),
    Country("CN", "China"),
    Country("CX", "Christmas Island"),
    Country("CC", "Cocos (Keeling) Islands"),
    Country("CO", "Colombia"),
    Country("KM", "Comoros"),
    Country("CG", "Congo"),
    Country("CD", "Congo, the Democratic Republic of the"),
    Country("CK", "Cook Islands"),
    Country("CR", "Costa Rica"),
    Country("CI", "Côte d'Ivoire"),
    Country("HR", "Croatia"),
    Country("CU", "Cuba"),
    Country("CW", "Curaçao"),
    Country("CY", "Cyprus"),
    Country("CZ", "Czech Republic"),
    Country("DK", "Denmark"),
    Country("DJ", "Djibouti"),
    Country("DM", "Dominica"),
    Country("DO", "Dominican Republic"),
    Country("EC", "Ecuador"),
    Country("EG", "Egypt"),
    Country("SV", "El Salvador"),
    Country("GQ", "Equatorial Guinea"),
    Country("ER", "Eritrea"),
    Country("EE", "Estonia"),
    Country("SZ", "Eswatini"),
    Country("ET", "Ethiopia"),
    Country("FK", "Falkland Islands (Malvinas)"),
    Country("FO", "Faroe Islands"),
    Country("FJ", "Fiji"),
    Country("FI", "Finland"),
    Country("FR", "France"),
    Country("GF", "French Guiana"),
    Country("PF", "French Polynesia"),
    Country("TF", "French Southern Territories"),
    Country("GA", "Gabon"),
    Country("GM", "Gambia"),
    Country("GE", "Georgia"),
    Country("DE", "Germany"),
    Country("GH", "Ghana"),
    Country("GI", "Gibraltar"),
    Country("GR", "Greece"),
    Country("GL", "Greenland"),
    Country("GD", "Grenada"),
    Country("GP", "Guadeloupe"),
    Country("GU", "Guam"),
    Country("GT", "Guatemala"),
    Country("GG", "Guernsey"),
    Country("GN", "Guinea"),
    Country("GW", "Guinea-Bissau"),
    Country("GY", "Guyana"),
    Country("HT", "Haiti"),
    Country("HM", "Heard Island and McDonald Islands"),
    Country("VA", "Holy See (Vatican City State)"),
    Country("HN", "Honduras"),
    Country("HK", "Hong Kong"),
    Country("HU", "Hungary"),
    Country("IS", "Iceland"),
    Country("IN", "India"),
    Country("ID", "Indonesia"),
    Country("IR", "Iran, Islamic Republic of"),
    Country("IQ", "Iraq"),
    Country("IE", "Ireland"),
    Country("IM", "Isle of Man"),
    Country("IL", "Israel"),
    Country("IT", "Italy"),
    Country("JM", "Jamaica"),
    Country("JP", "Japan"),
    Country("JE", "Jersey"),
    Country("JO", "Jordan"),
    Country("KZ", "Kazakhstan"),
    Country("KE", "Kenya"),
    Country("KI", "Kiribati"),
    Country("KP", "Korea, Democratic People's Republic of"),
    Country("KR", "Korea, Republic of"),
    Country("KW", "Kuwait"),
    Country("KG", "Kyrgyzstan"),
    Country("LA", "Lao People's Democratic Republic"),
    Country("LV", "Latvia"),
    Country("LB", "Lebanon"),
    Country("LS", "Lesotho"),
    Country("LR", "Liberia"),
    Country("LY", "Libya"),
    Country("LI", "Liechtenstein"),
    Country("LT", "Lithuania"),
    Country("LU", "Luxembourg"),
    Country("MO", "Macao"),
    Country("MG", "Madagascar"),
    Country("MW", "Malawi"),
    Country("MY", "Malaysia"),
    Country("MV", "Maldives"),
    Country("ML", "Mali"),
    Country("MT", "Malta"),
    Country("MH", "Marshall Islands"),
    Country("MQ", "Martinique"),
    Country("MR", "Mauritania"),
    Country("MU", "Mauritius"),
    Country("YT", "Mayotte"),
    Country("MX", "Mexico"),
    Country("FM", "Micronesia, Federated States of"),
    Country("MD", "Moldova, Republic of"),
    Country("MC", "Monaco"),
    Country("MN", "Mongolia"),
    Country("ME", "Montenegro"),
    Country("MS", "Montserrat"),
    Country("MA", "Morocco"),
    Country("MZ", "Mozambique"),
    Country("MM", "Myanmar"),
    Country("NA", "Namibia"),
    Country("NR", "Nauru"),
    Country("NP", "Nepal"),
    Country("NL", "Netherlands"),
    Country("NC", "New Caledonia"),
    Country("NZ", "New Zealand"),
    Country("NI", "Nicaragua"),
    Country("NE", "Niger"),
    Country("NG", "Nigeria"),
    Country("NU", "Niue"),
    Country("NF", "Norfolk Island"),
    Country("MK", "North Macedonia"),
    Country("MP", "Northern Mariana Islands"),
    Country("NO", "Norway"),
    Country("OM", "Oman"),
    Country("PK", "Pakistan"),
    Country("PW", "Palau"),
    Country("PS", "Palestine, State of"),
    Country("PA", "Panama"),
    Country("PG", "Papua New Guinea"),
    Country("PY", "Paraguay"),
    Country("PE", "Peru"),
    Country("PH", "Philippines"),
    Country("PN", "Pitcairn"),
    Country("PL", "Poland"),
    Country("PT", "Portugal"),
    Country("PR", "Puerto Rico"),
    Country("QA", "Qatar"),
    Country("RE", "Réunion"),
    Country("RO", "Romania"),
    Country("RU", "Russian Federation"),
    Country("RW", "Rwanda"),
    Country("BL", "Saint Barthélemy"),
    Country("SH", "Saint Helena, Ascension and Tristan da Cunha"),
    Country("KN", "Saint Kitts and Nevis"),
    Country("LC", "Saint Lucia"),
    Country("MF", "Saint Martin (French part)"),
    Country("PM", "Saint Pierre and Miquelon"),
    Country("VC", "Saint Vincent and the Grenadines"),
    Country("WS", "Samoa"),
    Country("SM", "San Marino"),
    Country("ST", "Sao Tome and Principe"),
    Country("SA", "Saudi Arabia"),
    Country("SN", "Senegal"),
    Country("RS", "Serbia"),
    Country("SC", "Seychelles"),
    Country("SL", "Sierra Leone"),
    Country("SG", "Singapore"),
    Country("SX", "Sint Maarten (Dutch part)"),
    Country("SK", "Slovakia"),
    Country("SI", "Slovenia"),
    Country("SB", "Solomon Islands"),
    Country("SO", "Somalia"),
    Country("ZA", "South Africa"),
    Country("GS", "South Georgia and the South Sandwich Islands"),
    Country("SS", "South Sudan"),
    Country("ES", "Spain"),
    Country("LK", "Sri Lanka"),
    Country("SD", "Sudan"),
    Country("SR", "Suriname"),
    Country("SJ", "Svalbard and Jan Mayen"),
    Country("SE", "Sweden"),
    Country("CH", "Switzerland"),
    Country("SY", "Syrian Arab Republic"),
    Country("TW", "Taiwan, Province of China"),
    Country("TJ", "Tajikistan"),
    Country("TZ", "Tanzania, United Republic of"),
    Country("TH", "Thailand"),
    Country("TL", "Timor-Leste"),
    Country("TG", "Togo"),
    Country("TK", "Tokelau"),
    Country("TO", "Tonga"),
    Country("TT", "Trinidad and Tobago"),
    Country("TN", "Tunisia"),
    Country("TR", "Turkey"),
    Country("TM", "Turkmenistan"),
    Country("TC", "Turks and Caicos Islands"),
    Country("TV", "Tuvalu"),
    Country("UG", "Uganda"),
    Country("UA", "Ukraine"),
    Country("AE", "United Arab Emirates"),
    Country("GB", "United Kingdom"),
    Country("US", "United States"),
    Country("UM", "United States Minor Outlying Islands"),
    Country("UY", "Uruguay"),
    Country("UZ", "Uzbekistan"),
    Country("VU", "Vanuatu"),
    Country("VE", "Venezuela, Bolivarian Republic of"),
    Country("VN", "Viet Nam"),
    Country("VG", "Virgin Islands, British"),
    Country("VI", "Virgin Islands, U.S."),
    Country("WF", "Wallis and Futuna"),
    Country("EH", "Western Sahara"),
    Country("YE", "Yemen"),
    Country("ZM", "Zambia"),
    Country("ZW", "Zimbabwe")
)


private fun validatePhoneNumber(
    context: android.content.Context,
    countryCode: String,
    phoneNumber: String
): Boolean {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()
    return try {
        val parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, countryCode)
        phoneNumberUtil.isValidNumber(parsedPhoneNumber)
    } catch (e: NumberParseException) {
        false
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}