package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.ui.screens.Register1Screen
import com.example.jetpackcompose.ui.screens.Register2Screen
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme // Asegúrate de tener un archivo de tema

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

// Define las rutas de navegación
object AppDestinations {
    const val REGISTER1 = "register1"
    const val REGISTER2 = "register2"
}

@OptIn(ExperimentalAnimationApi::class) // Necesario para las animaciones de transición
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: SharedViewModel = viewModel() // Obtiene la instancia del ViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.REGISTER1 // La primera pantalla
    ) {
        // Pantalla de Registro 1
        composable(
            route = AppDestinations.REGISTER1,
            enterTransition = { fadeIn(animationSpec = tween(500)) }, // Animación de entrada
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500)) }, // Animación al salir hacia pantalla 2
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(500)) }, // Animación al volver desde pantalla 2
            popExitTransition = { fadeOut(animationSpec = tween(500)) } // Animación al salir (pop)
        ) {
            Register1Screen(
                navController = navController,
                viewModel = sharedViewModel
            )
        }

        // Pantalla de Registro 2
        composable(
            route = AppDestinations.REGISTER2,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500)) }, // Animación al entrar desde pantalla 1
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(500)) }, // Animación al volver a pantalla 1
            popExitTransition = { fadeOut(animationSpec = tween(500)) } // No necesitamos popEnter específico aquí si siempre volvemos a reg1 limpiando
        ) {
            Register2Screen(
                navController = navController,
                viewModel = sharedViewModel
            )
        }
    }
}