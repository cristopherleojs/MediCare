package com.example.medicare.data.repositoriesx

import com.example.medicare.data.local.dao.UsuarioDao
import com.example.medicare.data.local.entities.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {
    // Aquí solo va la lógica de usuarios, nada de enfermedades
    suspend fun insertarUsuario(usuario: Usuario) = usuarioDao.insertarUsuario(usuario)
    suspend fun obtenerUsuarioPorEmail(email: String) = usuarioDao.obtenerUsuarioPorEmail(email)
}