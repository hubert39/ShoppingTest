package com.example.shoppingapp.login

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoppingapp.login.Routes.MainRoute.Home.toHome

@Composable
fun LoginScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val message = remember { mutableStateOf("") }

        LoginHeader()
        Spacer(modifier = Modifier.height(16.dp))
        LoginFields(
            username.value,
            password.value,
            onUsernameChange = { username.value = it },
            onPasswordChange = { password.value = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoginFooter(
            onSignInClick = {
                if (username.value.isNotEmpty() && password.value.isNotEmpty()) {
                    if (username.value == "admin" && password.value == "1234") {
                        message.value = "Login Successful"
                        navController.toHome()
                    } else {
                        //message = mutableStateOf("Invalid username or password")
                        message.value = "Invalid username or password"
                    }
                } else {
                    message.value = "Please enter username and password"
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
fun LoginHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Please sign in to continue",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.semantics {
                contentDescription = "Please sign in to continue"
            })
    }
}

@Composable
fun LoginFields(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column {
        TextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text(text = "Username") },
            placeholder = { Text(text = "Enter your username") },
            modifier = Modifier.fillMaxSize().semantics {
                contentDescription = "UsernameTextField"
            }
            //modifier = Modifier.fillMaxSize().testTag("UsernameTextField")
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxSize().semantics {
                contentDescription = "PasswordTextField"
            }
            //modifier = Modifier.fillMaxSize().testTag("PasswordTextField")
        )
    }
}

@Composable
fun LoginFooter(onSignInClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onSignInClick,
            modifier = Modifier.fillMaxWidth().semantics {
                contentDescription = "LoginButton"
            }
            //modifier = Modifier.fillMaxWidth().testTag("LoginButton")
        ) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}