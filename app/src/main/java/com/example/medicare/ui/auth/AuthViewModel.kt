package com.example.medicare.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.local.entity.Usuario
import com.example.medicare.data.repositories.UsuarioRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UsuarioRepository) : ViewModel() {
    private val _usuarioLogueado = MutableLiveData<Usuario?>()
    val usuarioLogueado = _usuarioLogueado
    private val _mensajeError = MutableLiveData<String>()
    val mensajeError = _mensajeError

    fun iniciarSesion(correo: String, contrasena: String) {
        viewModelScope.launch {
            val usuario = repository.iniciarSesion(correo, contrasena)
            if (usuario != null) {
                _usuarioLogueado.value = usuario
            } else {
                _mensajeError.value = "Correo o contraseña incorrectos"
            }
        }
    }

    fun registrarUsuario(nombre: String, correo: String, contrasena: String) {
        viewModelScope.launch {
            val nuevoUsuario = Usuario(nombre = nombre, correo = correo, contrasena = contrasena)
            val id = repository.registrarUsuario(nuevoUsuario)
            if (id > 0) {
                _usuarioLogueado.value = nuevoUsuario
            } else {
                _mensajeError.value = "Error al registrar"
            }
        }
    }
}