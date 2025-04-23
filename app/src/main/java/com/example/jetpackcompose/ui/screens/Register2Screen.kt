package com.example.jetpackcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcompose.AppDestinations
import com.example.jetpackcompose.SharedViewModel

@Composable
fun Register2Screen(navController: NavController, viewModel: SharedViewModel) {

    val context = LocalContext.current // Necesario para mostrar Toasts

    // Estados para los campos de texto de esta pantalla
    var email by remember { mutableStateOf(viewModel.getEmail().value ?: "") }
    var password by remember { mutableStateOf("") } // No pre-llenamos la contraseña
    var confirmPassword by remember { mutableStateOf("") }

    // Observa el email del ViewModel por si se navega hacia atrás y adelante
    val emailState = viewModel.getEmail().observeAsState("")
    LaunchedEffect(emailState.value) { email = emailState.value ?: "" }


    val scrollState = rememberScrollState()
    val primaryColor = Color(0xFFEF7171)
    val buttonColor = Color(0xFFB71C1C)
    val textColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Comienza con tu registro", // Podrías cambiar el título aquí
            fontSize = 28.sp,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Ingresa tu correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().InputStyle(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Crea tu contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().InputStyle(),
            visualTransformation = PasswordVisualTransformation(), // Oculta la contraseña
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirma tu contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().InputStyle(),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() }, // Vuelve a la pantalla anterior (Register1)
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Regresar", color = textColor)
            }

            Button(
                onClick = {
                    if (password.isEmpty() || confirmPassword.isEmpty()) {
                        Toast.makeText(context, "Las contraseñas no pueden estar vacías", Toast.LENGTH_SHORT).show()
                    } else if (password != confirmPassword) {
                        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    } else {
                        // Guardar datos finales
                        viewModel.setEmail(email)
                        viewModel.setPassword(password)

                        // Mostrar resumen
                        val fn = viewModel.getFirstName().value ?: ""
                        val ln = viewModel.getLastName().value ?: ""
                        val dni = viewModel.getDni().value ?: ""
                        val phone = viewModel.getPhone().value ?: ""
                        val summary = "Registrado:\n$fn $ln\nDNI: $dni\nTel: $phone\nEmail: $email"
                        Toast.makeText(context, summary, Toast.LENGTH_LONG).show()

                        // Volver al inicio limpiando la pila de atrás
                        navController.navigate(AppDestinations.REGISTER1) {
                            popUpTo(AppDestinations.REGISTER1) { inclusive = true } // Limpia hasta Register1 inclusive
                            launchSingleTop = true // Evita múltiples instancias de Register1
                        }
                        // Opcionalmente podrías resetear el ViewModel aquí si quieres que los campos
                        // estén vacíos la próxima vez que se inicie el registro.
                        // viewModel.reset() // Necesitarías implementar este método en el ViewModel
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text("Registrarse", color = textColor)
            }
        }
    }
}

