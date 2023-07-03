package com.example.travelapp

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelapp.sign_in.SignInState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
val openApp = mutableStateOf(false)

@Composable
fun Login(signInClick: () -> Unit){
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {//App Title
            Text(
                modifier = Modifier
                    .padding(30.dp)
                    .width(500.dp),
                fontSize = 60.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                text = "Welcome to TRAVELYZE")
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {// Login Fields
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {// Username/Email

                    var text by rememberSaveable { mutableStateOf("") }
                    val focusManager = LocalFocusManager.current

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Email") },
                        placeholder = { Text("example@gmail.com") },
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                        modifier = Modifier.border(width = 1.dp, color = Color.Black)
                    )
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {//Password
                    var text by rememberSaveable { mutableStateOf("") }
                    val focusManager = LocalFocusManager.current
                    var passwordVisible by rememberSaveable { mutableStateOf(false) }

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Password") },
                        placeholder = { Text("****") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .border(width = 1.dp, color = Color.Black),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            val description = if (passwordVisible) "Hide password" else "Show password"

                            IconButton(onClick = {passwordVisible = !passwordVisible}){
                                Icon(imageVector  = image, description)
                            }
                        }
                    )
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {//Login Button

                    Button(
                        onClick = { signInClick},
                        modifier = Modifier
                            .padding(start = 15.dp, top = 20.dp)
                            .size(width = 100.dp, height = 50.dp)
                        ) {
                        Text(text = "Login", color = Color.White)


                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {//Forgot Password
            /*TODO Make "Click Here" a link*/
            Text(text = "Forgot your password? Click here.", modifier = Modifier.padding(top = 20.dp))
        }
    }
}

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Login(onSignInClick)
}

