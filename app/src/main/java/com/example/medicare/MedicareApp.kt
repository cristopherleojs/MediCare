package com.example.medicare

import android.app.Application
import com.example.medicare.data.local.database.AppDatabase
import com.example.medicare.data.repositories.UsuarioRepository

class MedicareApp : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val usuarioRepository by lazy { UsuarioRepository(database.usuarioDao()) }
}