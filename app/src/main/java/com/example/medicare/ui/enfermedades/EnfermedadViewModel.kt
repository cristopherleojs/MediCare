package com.example.medicare.ui.enfermedades

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.remote.EnfermedadRequest
import com.example.medicare.data.remote.EnfermedadResponse
import com.example.medicare.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class EnfermedadViewModel : ViewModel() {
    var listaEnfermedades = mutableStateListOf<EnfermedadResponse>()
        private set

    fun cargarEnfermedades(idUsuario: Int) {
        viewModelScope.launch {
            try {
                val respuesta = RetrofitClient.enfermedadService.obtenerEnfermedades(idUsuario)
                if (respuesta.isSuccessful) {
                    listaEnfermedades.clear() // Limpia para evitar duplicados
                    respuesta.body()?.let { listaEnfermedades.addAll(it) }
                }
            } catch (e: Exception) {
                Log.e("MEDICARE_API", "Error al cargar: ${e.message}")
            }
        }
    }

    fun guardarEnfermedad(idUsuario: Int, nombre: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val request = EnfermedadRequest(idUsuario, nombre)
                val respuesta = RetrofitClient.enfermedadService.agregarEnfermedad(request)
                if (respuesta.isSuccessful) {
                    cargarEnfermedades(idUsuario) // Refresca la lista
                    onSuccess()
                }
            } catch (e: Exception) {
                Log.e("MEDICARE_API", "Error al guardar: ${e.message}")
            }
        }
    }
}