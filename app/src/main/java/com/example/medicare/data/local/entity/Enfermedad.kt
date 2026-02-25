package com.example.medicare.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "enfermedades",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["idUsuario"],
            childColumns = ["idUsuarioFk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Enfermedad(
    @PrimaryKey(autoGenerate = true)
    val idEnfermedad: Int = 0,

    @ColumnInfo(name = "id_usuario_fk")
    val idUsuarioFk: Int,

    @ColumnInfo(name = "nombre_enfermedad")
    val nombreEnfermedad: String
)