package com.example.shoppingapp.shoppingcart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoppingapp.login.Routes.MainRoute.CheckOutOverview.toCheckOutOverview

@Composable
fun CheckOutInfoScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val firstName = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val zipCode = remember { mutableStateOf("") }
        val message = remember { mutableStateOf("") }

        CheckOutInfoHeader()
        Spacer(modifier = Modifier.height(16.dp))
        CheckOutInfoFields(
            firstName.value,
            lastName.value,
            zipCode.value,
            onFirstNameChange = { firstName.value = it },
            onLastNameChange = { lastName.value = it },
            onZipCodeChange = { zipCode.value = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CheckOutInfoFooter(
            onCheckOutInfoClick = {
                if (firstName.value.isNotEmpty() && lastName.value.isNotEmpty() && zipCode.value.isNotEmpty()) {
                    message.value = "Check out successful"
                    navController.toCheckOutOverview()
                } else {
                    message.value = "Please enter all fields"
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (message.value.isNotEmpty()) {
            Text(text = message.value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun CheckOutInfoHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Check out your information",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.semantics {
                contentDescription = "Check out your information"
            }
        )
    }
}

@Composable
fun CheckOutInfoFields(
    firstName: String,
    lastName: String,
    zipCode: String,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onZipCodeChange: (String) -> Unit
) {
    Column {
        TextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            label = { Text(text = "First Name") },
            placeholder = { Text(text = "Enter your first name") },
            modifier = Modifier.fillMaxSize().semantics {
                contentDescription = "FirstNameTextField"
            }
            //modifier = Modifier.fillMaxSize().testTag("FirstNameTextField")
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = lastName,
            onValueChange = onLastNameChange,
            label = { Text(text = "Last Name") },
            placeholder = { Text(text = "Enter your last name") },
            modifier = Modifier.fillMaxSize().semantics {
                contentDescription = "LastNameTextField"
            }
            //modifier = Modifier.fillMaxSize().testTag("LastNameTextField"),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = zipCode,
            onValueChange = onZipCodeChange,
            label = { Text(text = "Zip Code") },
            placeholder = { Text(text = "Enter your zip code") },
            modifier = Modifier.fillMaxSize().semantics {
                contentDescription = "ZipCodeTextField"
            }
            //modifier = Modifier.fillMaxSize().testTag("ZipCodeTextField"),
        )
    }
}

@Composable
fun CheckOutInfoFooter(onCheckOutInfoClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onCheckOutInfoClick,
            modifier = Modifier.fillMaxWidth().semantics {
                contentDescription = "ContinueButton"
            }
        ) {
            Text(text = "Continue")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckOutInfoScreenPreview() {
    CheckOutInfoScreen(navController = NavController(LocalContext.current))
}