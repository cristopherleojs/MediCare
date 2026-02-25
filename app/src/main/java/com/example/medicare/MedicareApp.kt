package com.example.medicare

import android.app.Application
import com.example.medicare.data.local.database.AppDatabase

class MedicareApp : Application() {
    // Esto crea la base de datos de forma "perezosa" (solo cuando se necesita por primera vez)
    val database by lazy { AppDatabase.getDatabase(this) }
}