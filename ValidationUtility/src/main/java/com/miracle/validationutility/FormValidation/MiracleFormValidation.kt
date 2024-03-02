package com.miracle.validationutility.FormValidation

object MiracleFormValidation {

    fun validateForm(
        userID: String,
        firstName: String,
        lastName: String,
        phone: String,
        email: String,
        pass1: String,
        pass2: String,
        zip: String
    ): MutableMap<String, String> {
        val errors = mutableMapOf<String, String>()

        if (!userID.matches(Regex("^[a-zA-Z0-9]*$"))) {
            errors["user_id"] = "Invalid User ID"
        } else {
            errors["user_id"] = ""
        }

        if (!firstName.matches(Regex("^[a-zA-Z]*$"))) {
            errors["first_name"] = "Invalid First Name"
        } else {
            errors["first_name"] = ""
        }

        if (!lastName.matches(Regex("^[a-zA-Z]*$"))) {
            errors["last_name"] = "Invalid Last Name"
        } else {
            errors["last_name"] = ""
        }

        if (!phone.matches(Regex("^[0-9]*$"))) {
            errors["phone"] = "Invalid Phone Number"
        } else {
            errors["phone"] = ""
        }

        if (!email.matches(Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"))) {
            errors["email"] = "Invalid Email ID"
        } else {
            errors["email"] = ""
        }

        if (pass1.length < 6) {
            errors["pass1"] = "Password must be at least 6 characters"
        } else {
            errors["pass1"] = ""
        }

        if (pass1 != pass2) {
            errors["pass2"] = "Passwords do not match"
        } else {
            errors["pass2"] = ""
        }

        if (!zip.matches(Regex("^[0-9]*$"))) {
            errors["zip"] = "Invalid ZIP Code"
        } else {
            errors["zip"] = ""
        }

        return errors
    }


}