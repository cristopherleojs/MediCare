package com.example.medicare.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medicare.data.local.entity.Usuario
import com.example.medicare.data.local.entity.Enfermedad
import com.example.medicare.data.local.dao.UsuarioDao
import com.example.medicare.data.local.dao.EnfermedadDao

@Database(entities = [Usuario::class, Enfermedad::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun enfermedadDao(): EnfermedadDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "medicare_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}