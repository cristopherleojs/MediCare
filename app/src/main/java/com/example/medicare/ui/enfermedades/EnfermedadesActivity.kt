package com.example.medicare.ui.enfermedades

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.medicare.ui.theme.MediCareTheme

class EnfermedadesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idUsuario = intent.getIntExtra("ID_USUARIO", 1)

        setContent {
            MediCareTheme {
                var pantallaActual by remember { mutableStateOf("historial") }

                when (pantallaActual) {
                    "historial" -> {
                        EnfermedadesScreen(
                            idUsuario = idUsuario,
                            onAgregarClick = { pantallaActual = "registro" },
                            onVolver = { finish() }
                        )
                    }
                    "registro" -> {
                        RegistrarEnfermedadScreen(
                            idUsuario = idUsuario,
                            onVolver = { pantallaActual = "historial" }
                        )
                    }
                }
            }
        }
    }
}