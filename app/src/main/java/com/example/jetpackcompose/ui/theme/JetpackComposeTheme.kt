package com.example.jetpackcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color // Importa Color

// Define tus paletas de colores aquí
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFB71C1C), // Rojo oscuro para botones
    secondary = Color(0xFFEF7171), // Rojo claro para fondo
    tertiary = Color(0xFFCCCCCC),
    background = Color(0xFFEF7171), // Fondo principal
    surface = Color(0xFFFFFFFF), // Para las cards o inputs blancos
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFB71C1C),
    secondary = Color(0xFFEF7171),
    tertiary = Color(0xFF757575), // Color para hint text
    background = Color(0xFFEF7171),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = Color.White, // Texto sobre el fondo rojo
    onSurface = Color.Black, // Texto sobre superficies blancas (inputs)

    /* Other default colors to override
    surfaceVariant = Color(0xFFF0F0F0),
    outline = Color(0xFFBDBDBD) // Color del borde del input original
    */
)

@Composable
fun JetpackComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        /* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } */
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    // Puedes definir aquí también Typography y Shapes si es necesario
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asegúrate de tener un objeto Typography definido
        content = content
    )
}

// Necesitarás también un archivo Typography.kt (puedes usar el generado por defecto)
// import androidx.compose.material3.Typography
// val Typography = Typography(...)