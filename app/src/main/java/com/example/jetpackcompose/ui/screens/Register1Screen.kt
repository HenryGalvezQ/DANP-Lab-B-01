package com.example.jetpackcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState // Para observar LiveData
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcompose.AppDestinations
import com.example.jetpackcompose.SharedViewModel

@Composable
fun Register1Screen(navController: NavController, viewModel: SharedViewModel) {

    // Observa los valores del ViewModel para pre-llenar si se vuelve atrás
    val firstNameState = viewModel.getFirstName().observeAsState("")
    val lastNameState = viewModel.getLastName().observeAsState("")
    val dniState = viewModel.getDni().observeAsState("")
    val phoneState = viewModel.getPhone().observeAsState("")

    // Estados locales para manejar la entrada del usuario directamente
    var firstName by remember { mutableStateOf(firstNameState.value ?: "") }
    var lastName by remember { mutableStateOf(lastNameState.value ?: "") }
    var dni by remember { mutableStateOf(dniState.value ?: "") }
    var phone by remember { mutableStateOf(phoneState.value ?: "") }

    // Actualiza los estados locales si los del ViewModel cambian (al volver atrás)
    LaunchedEffect(firstNameState.value) { firstName = firstNameState.value ?: "" }
    LaunchedEffect(lastNameState.value) { lastName = lastNameState.value ?: "" }
    LaunchedEffect(dniState.value) { dni = dniState.value ?: "" }
    LaunchedEffect(phoneState.value) { phone = phoneState.value ?: "" }


    val scrollState = rememberScrollState()
    val primaryColor = Color(0xFFEF7171) // Color de fondo original
    val buttonColor = Color(0xFFB71C1C) // Color de botón original
    val textColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(16.dp)
            .verticalScroll(scrollState), // Para que sea scrollable si el contenido no cabe
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Comienza con tu registro",
            fontSize = 28.sp,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campos de texto
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Ingresa tus nombres") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().InputStyle(),
            colors = TextFieldDefaults.colors( // Usar TextFieldDefaults.colors para Material 3
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, // Ocultar línea inferior si se quiere
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Ingresa tus apellidos") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().InputStyle(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it.filter { char -> char.isDigit() } }, // Solo números
            label = { Text("Ingresa tu número de DNI") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().InputStyle(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it.filter { char -> char.isDigit() } }, // Solo números
            label = { Text("Ingresa tu Número de teléfono") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().InputStyle(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Espacio entre botones
        ) {
            Button(
                onClick = {
                    // En la primera pantalla, "Regresar" podría cerrar la app o no hacer nada
                    // Si se navega a esta pantalla desde otro lugar, sí haría popBackStack
                    // navController.popBackStack() // Descomentar si hay pantalla anterior
                    // O podrías cerrar la actividad: (context as? Activity)?.finish()
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .weight(1f) // Ocupa espacio disponible
                    .padding(end = 8.dp) // Espacio entre botones
            ) {
                Text("Regresar", color = textColor)
            }

            Button(
                onClick = {
                    // Guardar datos en ViewModel antes de navegar
                    viewModel.setFirstName(firstName)
                    viewModel.setLastName(lastName)
                    viewModel.setDni(dni)
                    viewModel.setPhone(phone)
                    // Navegar a la siguiente pantalla
                    navController.navigate(AppDestinations.REGISTER2)
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .weight(1f) // Ocupa espacio disponible
                    .padding(start = 8.dp) // Espacio entre botones
            ) {
                Text("Siguiente", color = textColor)
            }
        }
    }
}

// Modifier reutilizable para el estilo de los inputs
fun Modifier.InputStyle(): Modifier = this
    .clip(RoundedCornerShape(14.dp)) // Bordes redondeados
    .background(Color.White) // Fondo blanco