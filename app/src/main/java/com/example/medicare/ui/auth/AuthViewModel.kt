package com.example.medicare.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.local.entity.Usuario
import com.example.medicare.data.repository.UsuarioRepository
import kotlinx.coroutines.launch

// El ViewModel recibe el Repositorio para poder pedirle los datos
class AuthViewModel(private val repository: UsuarioRepository) : ViewModel() {

    // LiveData para que la pantalla observe si el login fue exitoso
    private val _usuarioLogueado = MutableLiveData<Usuario?>()
    val usuarioLogueado: LiveData<Usuario?> = _usuarioLogueado

    // LiveData para mostrar mensajes de error (ej. "Contraseña incorrecta")
    private val _mensajeError = MutableLiveData<String>()
    val mensajeError: LiveData<String> = _mensajeError

    // Función que será llamada desde tu pantalla de Login cuando presionen el botón
    fun iniciarSesion(correo: String, contrasena: String) {
        // Validación rápida
        if (correo.isBlank() || contrasena.isBlank()) {
            _mensajeError.value = "Por favor, llena todos los campos"
            return
        }

        // viewModelScope.launch ejecuta esto en un hilo secundario para no congelar la app
        viewModelScope.launch {
            val usuario = repository.iniciarSesion(correo, contrasena)
            if (usuario != null) {
                _usuarioLogueado.value = usuario // ¡Login exitoso!
            } else {
                _mensajeError.value = "Correo o contraseña incorrectos" // Falló
            }
        }
    }

    // Función que será llamada desde tu pantalla de Registro
    fun registrarUsuario(nombre: String, correo: String, contrasena: String) {
        if (nombre.isBlank() || correo.isBlank() || contrasena.isBlank()) {
            _mensajeError.value = "Todos los campos son obligatorios"
            return
        }

        viewModelScope.launch {
            // Creamos el objeto Usuario (el ID se genera solo porque le pusimos autoGenerate = true)
            val nuevoUsuario = Usuario(nombre = nombre, correo = correo, contrasena = contrasena)

            // Le decimos al repositorio que lo guarde en Room
            val idGenerado = repository.registrarUsuario(nuevoUsuario)

            if (idGenerado > 0) {
                // Si el ID es mayor a 0, se guardó bien. Hacemos auto-login.
                _usuarioLogueado.value = nuevoUsuario
            } else {
                _mensajeError.value = "Error al guardar en la base de datos"
            }
        }
    }
}